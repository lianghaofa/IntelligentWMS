package org.iwms.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.model.BinSize;
import org.iwms.warehouse.model.ResponseMsg;
import org.iwms.warehouse.service.BinSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leung
 */
@RestController
public class BinSizeController {

    @Autowired
    private BinSizeService binSizeService;

    @RequestMapping(value = "/binsize")
    public String binSizePage(int page, @RequestParam(value = "binsize_name__icontains", required = false) String name){

        IPage<BinSize> usersByPage = binSizeService.getBinSizeByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<BinSize> drivers = usersByPage.getRecords();

        ResponseMsg<BinSize> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/binsize/{id}")
    public String binSizeEdit(@RequestBody BinSize binSize, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            binSize.setId(id);
        }
        binSizeService.binSizeEdit(binSize);
        return ResponseMsgUtil.writeObjectAsJsonString(binSize);
    }

    @PostMapping(value = "/binsize")
    public String binSizeAdd(@RequestBody BinSize binSize){
        if (null == binSize){
            return null;
        }
        binSizeService.binSizeAdd(binSize);
        return ResponseMsgUtil.writeObjectAsJsonString(binSize);
    }

    @DeleteMapping(value = "/binsize/{id}")
    public String binSizeDelete(@PathVariable("id") Long id){
        BinSize binSize = binSizeService.binSizeSelectById(id);
        if (null == binSize){
            return null;
        }
        binSizeService.setDeleteFlagTrue(binSize);
        return ResponseMsgUtil.writeObjectAsJsonString(binSize);
    }

    @GetMapping(value = "/binsize/file")
    public void binSizeFileDownload(HttpServletResponse response, String lang){

        List<BinSize> binProperties = binSizeService.getBinSizes(false); // 假设有一个DriverService来获取数据
        Class<BinSize> binSizeClass = BinSize.class;
        Field[] fields = binSizeClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (BinSize binSize : binProperties){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = binSize.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(binSize);
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
