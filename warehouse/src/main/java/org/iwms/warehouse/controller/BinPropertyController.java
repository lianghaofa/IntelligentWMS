package org.iwms.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.model.BinProperty;
import org.iwms.warehouse.model.ResponseMsg;
import org.iwms.warehouse.model.WareHouse;
import org.iwms.warehouse.service.BinPropertyService;
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
public class BinPropertyController {

    @Autowired
    private BinPropertyService binPropertyService;

    @RequestMapping(value = "/binproperty")
    public String binPropertyPage(int page, @RequestParam(value = "binproperty_name__icontains", required = false) String name){

        IPage<BinProperty> usersByPage = binPropertyService.getBinPropertyByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<BinProperty> drivers = usersByPage.getRecords();

        ResponseMsg<BinProperty> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/binproperty/{id}")
    public String binPropertyEdit(@RequestBody BinProperty binProperty, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            binProperty.setId(id);
        }
        binPropertyService.binPropertyEdit(binProperty);
        return ResponseMsgUtil.writeObjectAsJsonString(binProperty);
    }

    @PostMapping(value = "/binproperty")
    public String binPropertyAdd(@RequestBody BinProperty binProperty){
        if (null == binProperty){
            return null;
        }
        binPropertyService.binPropertyAdd(binProperty);
        return ResponseMsgUtil.writeObjectAsJsonString(binProperty);
    }

    @DeleteMapping(value = "/binproperty/{id}")
    public String binPropertyDelete(@PathVariable("id") Long id){
        BinProperty binProperty = binPropertyService.binPropertySelectById(id);
        if (null == binProperty){
            return null;
        }
        binPropertyService.setDeleteFlagTrue(binProperty);
        return ResponseMsgUtil.writeObjectAsJsonString(binProperty);
    }

    @GetMapping(value = "/binproperty/file")
    public void binPropertyFileDownload(HttpServletResponse response, String lang){

        List<BinProperty> binProperties = binPropertyService.getBinProperties(false); // 假设有一个DriverService来获取数据
        Class<BinProperty> binPropertyClass = BinProperty.class;
        Field[] fields = binPropertyClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (BinProperty binProperty : binProperties){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = binProperty.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(binProperty);
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
