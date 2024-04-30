package org.iwms.driver.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iwms.driver.constant.Constant;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.iwms.driver.model.ResponseMsg;
import org.iwms.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin
    @RequestMapping(value = "/driver")
    public String driverPage(int page){

        IPage<Driver> usersByPage = driverService.getUsersByPage(page, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Driver> drivers = usersByPage.getRecords();

        ResponseMsg<Driver> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        String result = null;
        try {
            result = objectMapper.writeValueAsString(responseMsg);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }

}
