package org.iwms.supplier.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.supplier.model.Supplier;
import org.iwms.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = "/supplier")
    public String supplierPage(int page, @RequestParam(value = "supplier_name__icontains", required = false) String name){

        IPage<Supplier> usersByPage = supplierService.getSupplierByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Supplier> drivers = usersByPage.getRecords();

        ResponseMsg<Supplier> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @RequestMapping("/provider/list")
    public List<String> list(){
        List<Supplier> suppliers = supplierService.getSuppliers(false);
        List<String> resList = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            resList.add(supplier.getSupplierName());
        }
        return resList;
    }

    @PutMapping(value = "/supplier/{id}")
    public String supplierEdit(@RequestBody Supplier supplier, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            supplier.setId(id);
        }
        supplierService.supplierEdit(supplier);
        return ResponseMsgUtil.writeObjectAsJsonString(supplier);
    }

    @PostMapping(value = "/supplier")
    public String supplierAdd(@RequestBody Supplier supplier){
        if (null == supplier){
            return null;
        }
        supplierService.supplierAdd(supplier);
        return ResponseMsgUtil.writeObjectAsJsonString(supplier);
    }

    @DeleteMapping(value = "/supplier/{id}")
    public String supplierDelete(@PathVariable("id") Long id){
        Supplier supplier = supplierService.supplierSelectById(id);
        if (null == supplier){
            return null;
        }
        supplierService.setDeleteFlagTrue(supplier);
        return ResponseMsgUtil.writeObjectAsJsonString(supplier);
    }

    @GetMapping(value = "/supplier/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Supplier> suppliers = supplierService.getSuppliers(false); // 假设有一个DriverService来获取数据
        Class<Supplier> driverClass = Supplier.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Supplier supplier : suppliers){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = supplier.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(supplier);
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
