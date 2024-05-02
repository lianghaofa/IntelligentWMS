package org.iwms.capital.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.capital.model.Capital;
import org.iwms.capital.service.CapitalService;
import org.iwms.common.web.model.ResponseMsg;
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
public class CapitalController {

    @Autowired
    private CapitalService capitalService;

    @RequestMapping(value = "/capital")
    public String capitalPage(int page, @RequestParam(value = "capital_name__icontains", required = false) String name){

        IPage<Capital> usersByPage = capitalService.getCapitalsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Capital> drivers = usersByPage.getRecords();

        ResponseMsg<Capital> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/capital/{id}")
    public String capitalEdit(@RequestBody Capital capital, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            capital.setId(id);
        }
        capitalService.capitalEdit(capital);
        return ResponseMsgUtil.writeObjectAsJsonString(capital);
    }

    @PostMapping(value = "/capital")
    public String capitalAdd(@RequestBody Capital capital){
        if (null == capital){
            return null;
        }
        capitalService.capitalAdd(capital);
        return ResponseMsgUtil.writeObjectAsJsonString(capital);
    }

    @DeleteMapping(value = "/capital/{id}")
    public String capitalDelete(@PathVariable("id") Long id){
        Capital capital = capitalService.capitalSelectById(id);
        if (null == capital){
            return null;
        }
        capitalService.setDeleteFlagTrue(capital);
        return ResponseMsgUtil.writeObjectAsJsonString(capital);
    }

    @GetMapping(value = "/capital/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Capital> capitals = capitalService.getCapitals(false); // 假设有一个DriverService来获取数据
        Class<Capital> driverClass = Capital.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Capital capital : capitals){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = capital.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(capital);
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
