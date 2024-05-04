package org.iwms.driver.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.driver.constant.Constant;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.iwms.driver.model.ResponseMsg;
import org.iwms.driver.service.DriverService;
import org.iwms.driver.utils.ExportCSVUtil;
import org.iwms.driver.utils.ResponseMsgUtil;
import org.iwms.driver.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author leung
 */
@RestController
public class DriverController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "/driver")
    public String driverPage(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "driver_name__icontains", required = false) String name){



        IPage<Driver> usersByPage = driverService.getDriversByPage(
                !StringUtil.isNotBlank(page) || "NAN".equals(page) ? 1 : Integer.parseInt(page), name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Driver> drivers = usersByPage.getRecords();

        ResponseMsg<Driver> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/driver/{id}")
    public String driverEdit(@RequestBody Driver driver, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            driver.setId(id);
        }
        driverService.driverEdit(driver);
        return ResponseMsgUtil.writeObjectAsJsonString(driver);
    }

    @PostMapping(value = "/driver")
    public String driverAdd(@RequestBody Driver driver){
        if (null == driver){
            return null;
        }
        driverService.driverAdd(driver);
        return ResponseMsgUtil.writeObjectAsJsonString(driver);
    }

    @DeleteMapping(value = "/driver/{id}")
    public String driverDelete(@PathVariable("id") Long id){
        Driver driver = driverService.driverSelectById(id);
        if (null == driver){
            return null;
        }
        driverService.setDeleteFlagTrue(driver);
        return ResponseMsgUtil.writeObjectAsJsonString(driver);
    }

    @GetMapping(value = "/driver/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Driver> drivers = driverService.getUsers(false); // 假设有一个DriverService来获取数据
        Class<Driver> driverClass = Driver.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Driver driver : drivers){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = driver.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(driver);
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
