package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsUnit;
import org.iwms.goods.service.GoodsUnitService;
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
public class GoodsUnitController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsUnitService goodsUnitService;

    @RequestMapping(value = "/goodsunit")
    public String goodsUnitPage(int page, @RequestParam(value = "goodsUnit_name__icontains", required = false) String name){

        IPage<GoodsUnit> usersByPage = goodsUnitService.getGoodsUnitsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsUnit> goodsUnits = usersByPage.getRecords();

        ResponseMsg<GoodsUnit> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsUnits.size());
        responseMsg.setResults(goodsUnits);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsunit/{id}")
    public String goodsUnitEdit(@RequestBody GoodsUnit goodsUnit, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsUnit.setId(id);
        }
        goodsUnitService.goodsUnitEdit(goodsUnit);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsUnit);
    }

    @PostMapping(value = "/goodsunit")
    public String goodsUnitAdd(@RequestBody GoodsUnit goodsUnit){
        if (null == goodsUnit){
            return null;
        }
        goodsUnitService.goodsUnitAdd(goodsUnit);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsUnit);
    }

    @DeleteMapping(value = "/goodsunit/{id}")
    public String goodsUnitDelete(@PathVariable("id") Long id){
        GoodsUnit goodsUnit = goodsUnitService.goodsUnitSelectById(id);
        if (null == goodsUnit){
            return null;
        }
        goodsUnitService.setDeleteFlagTrue(goodsUnit);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsUnit);
    }

    @GetMapping(value = "/goodsunit/file")
    public void goodsUnitFileDownload(HttpServletResponse response, String lang){

        List<GoodsUnit> goodsUnits = goodsUnitService.getUsers(false); // 假设有一个GoodsUnitService来获取数据
        Class<GoodsUnit> goodsUnitClass = GoodsUnit.class;
        Field[] fields = goodsUnitClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsUnit goodsUnit : goodsUnits){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsUnit.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsUnit);
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
