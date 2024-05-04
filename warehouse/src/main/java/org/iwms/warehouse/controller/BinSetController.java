package org.iwms.warehouse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.model.BinProperty;
import org.iwms.warehouse.model.BinSet;
import org.iwms.warehouse.model.BinSize;
import org.iwms.warehouse.model.ResponseMsg;
import org.iwms.warehouse.service.BinPropertyService;
import org.iwms.warehouse.service.BinSetService;
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
public class BinSetController {

    @Autowired
    private BinSetService binSetService;

    @Autowired
    private BinPropertyService binPropertyService;

    @Autowired
    private BinSizeService binSizeService;

    @RequestMapping(value = "/binset")
    public String binSetPage(int page, @RequestParam(value = "binset_name__icontains", required = false) String name){

        IPage<BinSet> usersByPage = binSetService.getBinSetByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<BinSet> drivers = usersByPage.getRecords();

        ResponseMsg<BinSet> responseMsg = new ResponseMsg<>();

        List<String> binSizeList = new ArrayList<>();
        List<BinSize> binSizes = binSizeService.getBinSizes(false);
        for (BinSize binSize : binSizes){
            binSizeList.add(binSize.getBinSize());
        }
        responseMsg.setBinSizeList(binSizeList);
        List<String> binPropertyList = new ArrayList<>();
        List<BinProperty> binProperties = binPropertyService.getBinProperties(false);
        for (BinProperty binProperty : binProperties){
            binPropertyList.add(binProperty.getBinProperty());
        }
        responseMsg.setBinPropertyList(binPropertyList);
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/binset/{id}")
    public String binSetEdit(@RequestBody BinSet binSet, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            binSet.setId(id);
        }
        binSetService.binSetEdit(binSet);
        return ResponseMsgUtil.writeObjectAsJsonString(binSet);
    }

    @PostMapping(value = "/binset")
    public String binSetAdd(@RequestBody BinSet binSet){
        if (null == binSet){
            return null;
        }
        binSetService.binSetAdd(binSet);
        return ResponseMsgUtil.writeObjectAsJsonString(binSet);
    }

    @DeleteMapping(value = "/binset/{id}")
    public String binSetDelete(@PathVariable("id") Long id){
        BinSet binSet = binSetService.binSetSelectById(id);
        if (null == binSet){
            return null;
        }
        binSetService.setDeleteFlagTrue(binSet);
        return ResponseMsgUtil.writeObjectAsJsonString(binSet);
    }

    @GetMapping(value = "/binset/file")
    public void binSetFileDownload(HttpServletResponse response, String lang){

        List<BinSet> binProperties = binSetService.getBinProperties(false); // 假设有一个DriverService来获取数据
        Class<BinSet> binSetClass = BinSet.class;
        Field[] fields = binSetClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (BinSet binSet : binProperties){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = binSet.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(binSet);
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
