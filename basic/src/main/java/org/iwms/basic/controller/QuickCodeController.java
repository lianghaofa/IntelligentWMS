package org.iwms.basic.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.basic.model.QuickCode;
import org.iwms.basic.model.ResponseMsg;
import org.iwms.basic.service.QuickCodeService;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.exception.SystemErrorType;
import org.iwms.common.core.exception.ValidationException;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author leung
 */
@RestController
public class QuickCodeController {

    static final Logger logger = LoggerFactory.getLogger(QuickCodeController.class);
    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private QuickCodeService quickCodeService;

    @RequestMapping(value = "/quickcode")
    public String quickCodePage(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "driver_name__icontains", required = false) String name){



        IPage<QuickCode> quickCodesByPage = quickCodeService.getQuickCodesByPage(
                !StringUtil.isNotBlank(page) || "NAN".equals(page) ? 1 : Integer.parseInt(page), name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<QuickCode> drivers = quickCodesByPage.getRecords();

        ResponseMsg<QuickCode> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/quickcode/{id}")
    public String quickCodeEdit(@RequestBody QuickCode quickCode, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            quickCode.setId(id);
        }
        quickCodeService.quickCodeEdit(quickCode);
        return ResponseMsgUtil.writeObjectAsJsonString(quickCode);
    }

    @PostMapping(value = "/quickcode")
    public String quickCodeAdd(@RequestBody QuickCode quickCode){
        if (null == quickCode){
            return null;
        }
        quickCodeService.quickCodeAdd(quickCode);
        return ResponseMsgUtil.writeObjectAsJsonString(quickCode);
    }

    @DeleteMapping(value = "/quickcode/{id}")
    public String quickCodeDelete(@PathVariable("id") Long id){
        QuickCode quickCode = quickCodeService.quickCodeSelectById(id);
        if (null == quickCode){
            return null;
        }
        quickCodeService.setDeleteFlagTrue(quickCode);
        return ResponseMsgUtil.writeObjectAsJsonString(quickCode);
    }

    @GetMapping(value = "/quickcode/file")
    public void quickCodeFileDownload(HttpServletResponse response, String lang){

        List<QuickCode> quickCodes = quickCodeService.getQuickCodes(false); // 假设有一个DriverService来获取数据
        Class<QuickCode> driverClass = QuickCode.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (QuickCode quickCode : quickCodes){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = quickCode.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(quickCode);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    objects.add(value);
                }
            }
            cellList.add(objects.toArray(new Object[objects.size()]));
        }
        String[] tableHeaderArr = tableHeaderList.toArray(new String[tableHeaderList.size()]);
        String fileName = "导出文件.csv";
        byte[] bytes = ExportCSVUtil.writeCsvAfterToBytes(tableHeaderArr, cellList);
        ExportCSVUtil.responseSetProperties(fileName,bytes, response);
    }


}
