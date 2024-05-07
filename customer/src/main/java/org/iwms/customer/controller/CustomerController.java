package org.iwms.customer.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.customer.model.Customer;
import org.iwms.customer.service.CustomerService;
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
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer")
    public String customerPage(int page, @RequestParam(value = "customer_name__icontains", required = false) String name){

        IPage<Customer> usersByPage = customerService.getCustomerByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Customer> drivers = usersByPage.getRecords();

        ResponseMsg<Customer> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/customer/{id}")
    public String customerEdit(@RequestBody Customer customer, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            customer.setId(id);
        }
        customerService.customerEdit(customer);
        return ResponseMsgUtil.writeObjectAsJsonString(customer);
    }

    @PostMapping(value = "/customer")
    public String customerAdd(@RequestBody Customer customer){
        if (null == customer){
            return null;
        }
        customerService.customerAdd(customer);
        return ResponseMsgUtil.writeObjectAsJsonString(customer);
    }

    @DeleteMapping(value = "/customer/{id}")
    public String customerDelete(@PathVariable("id") Long id){
        Customer customer = customerService.customerSelectById(id);
        if (null == customer){
            return null;
        }
        customerService.setDeleteFlagTrue(customer);
        return ResponseMsgUtil.writeObjectAsJsonString(customer);
    }

    @GetMapping(value = "/customer/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Customer> customers = customerService.getCustomers(false); // 假设有一个DriverService来获取数据
        Class<Customer> driverClass = Customer.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Customer customer : customers){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = customer.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(customer);
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
