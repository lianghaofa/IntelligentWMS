package org.iwms.warehouse.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.model.ResponseMsg;
import org.iwms.warehouse.model.WareHouse;
import org.iwms.warehouse.service.WareHouseService;
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
public class WareHouseController {

    @Autowired
    private WareHouseService wareHouseService;

    @RequestMapping(value = "/warehouse")
    public String wareHousePage(int page, @RequestParam(value = "wareHouse_name__icontains", required = false) String name){

        IPage<WareHouse> usersByPage = wareHouseService.getWareHouseByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<WareHouse> drivers = usersByPage.getRecords();

        ResponseMsg<WareHouse> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/warehouse/{id}")
    public String wareHouseEdit(@RequestBody WareHouse wareHouse, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            wareHouse.setId(id);
        }
        wareHouseService.wareHouseEdit(wareHouse);
        return ResponseMsgUtil.writeObjectAsJsonString(wareHouse);
    }

    @PostMapping(value = "/warehouse")
    public String wareHouseAdd(@RequestBody WareHouse wareHouse){
        if (null == wareHouse){
            return null;
        }
        wareHouseService.wareHouseAdd(wareHouse);
        return ResponseMsgUtil.writeObjectAsJsonString(wareHouse);
    }

    @DeleteMapping(value = "/warehouse/{id}")
    public String wareHouseDelete(@PathVariable("id") Long id){
        WareHouse wareHouse = wareHouseService.wareHouseSelectById(id);
        if (null == wareHouse){
            return null;
        }
        wareHouseService.setDeleteFlagTrue(wareHouse);
        return ResponseMsgUtil.writeObjectAsJsonString(wareHouse);
    }

    @GetMapping(value = "/warehouse/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<WareHouse> wareHouses = wareHouseService.getWareHouses(false); // 假设有一个DriverService来获取数据
        Class<WareHouse> driverClass = WareHouse.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (WareHouse wareHouse : wareHouses){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = wareHouse.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(wareHouse);
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
