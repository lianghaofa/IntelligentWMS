package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsSpecs;
import org.iwms.goods.service.GoodsSpecsService;
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
public class GoodsSpecsController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsSpecsService goodsSpecsService;

    @RequestMapping(value = "/goodsspecs")
    public String goodsSpecsPage(int page, @RequestParam(value = "goods_specs__icontains", required = false) String name){

        IPage<GoodsSpecs> usersByPage = goodsSpecsService.getGoodsSpecssByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsSpecs> goodsSpecss = usersByPage.getRecords();

        ResponseMsg<GoodsSpecs> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsSpecss.size());
        responseMsg.setResults(goodsSpecss);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsspecs/{id}")
    public String goodsSpecsEdit(@RequestBody GoodsSpecs goodsSpecs, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsSpecs.setId(id);
        }
        goodsSpecsService.goodsSpecsEdit(goodsSpecs);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsSpecs);
    }

    @PostMapping(value = "/goodsspecs")
    public String goodsSpecsAdd(@RequestBody GoodsSpecs goodsSpecs){
        if (null == goodsSpecs){
            return null;
        }
        goodsSpecsService.goodsSpecsAdd(goodsSpecs);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsSpecs);
    }

    @DeleteMapping(value = "/goodsspecs/{id}")
    public String goodsSpecsDelete(@PathVariable("id") Long id){
        GoodsSpecs goodsSpecs = goodsSpecsService.goodsSpecsSelectById(id);
        if (null == goodsSpecs){
            return null;
        }
        goodsSpecsService.setDeleteFlagTrue(goodsSpecs);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsSpecs);
    }

    @GetMapping(value = "/goodsspecs/file")
    public void goodsSpecsFileDownload(HttpServletResponse response, String lang){

        List<GoodsSpecs> goodsSpecss = goodsSpecsService.getUsers(false); // 假设有一个GoodsSpecsService来获取数据
        Class<GoodsSpecs> goodsSpecsClass = GoodsSpecs.class;
        Field[] fields = goodsSpecsClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsSpecs goodsSpecs : goodsSpecss){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsSpecs.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsSpecs);
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
