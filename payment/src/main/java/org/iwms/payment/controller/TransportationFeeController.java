package org.iwms.payment.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;

import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.payment.model.TransportationFee;
import org.iwms.payment.service.TransportationFeeService;
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
@RequestMapping("/payment")
public class TransportationFeeController {

    @Autowired
    private TransportationFeeService transportationFeeService;

    @RequestMapping(value = "/freight")
    public String capitalPage(int page, @RequestParam(value = "transportationFee_name__icontains", required = false) String name){

        IPage<TransportationFee> usersByPage = transportationFeeService.getTransportationFeesByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<TransportationFee> drivers = usersByPage.getRecords();

        ResponseMsg<TransportationFee> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/freight/{id}")
    public String capitalEdit(@RequestBody TransportationFee transportationFee, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            transportationFee.setId(id);
        }
        transportationFeeService.transportationFeeEdit(transportationFee);
        return ResponseMsgUtil.writeObjectAsJsonString(transportationFee);
    }

    @PostMapping(value = "/freight")
    public String transportationFeeAdd(@RequestBody TransportationFee transportationFee){
        if (null == transportationFee){
            return null;
        }
        transportationFeeService.transportationFeeAdd(transportationFee);
        return ResponseMsgUtil.writeObjectAsJsonString(transportationFee);
    }

    @DeleteMapping(value = "/freight/{id}")
    public String capitalDelete(@PathVariable("id") Long id){
        TransportationFee capital = transportationFeeService.transportationFeeSelectById(id);
        if (null == capital){
            return null;
        }
        transportationFeeService.setDeleteFlagTrue(capital);
        return ResponseMsgUtil.writeObjectAsJsonString(capital);
    }

    @GetMapping(value = "/freight/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<TransportationFee> capitals = transportationFeeService.getTransportationFees(false); // 假设有一个DriverService来获取数据
        Class<TransportationFee> driverClass = TransportationFee.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (TransportationFee capital : capitals){
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
