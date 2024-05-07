package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsShape;
import org.iwms.goods.service.GoodsShapeService;
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
public class GoodsShapeController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsShapeService goodsShapeService;

    @RequestMapping(value = "/goodsshape")
    public String goodsShapePage(int page, @RequestParam(value = "goods_shape__icontains", required = false) String name){

        IPage<GoodsShape> usersByPage = goodsShapeService.getGoodsShapesByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsShape> goodsShapes = usersByPage.getRecords();

        ResponseMsg<GoodsShape> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsShapes.size());
        responseMsg.setResults(goodsShapes);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsshape/{id}")
    public String goodsShapeEdit(@RequestBody GoodsShape goodsShape, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsShape.setId(id);
        }
        goodsShapeService.goodsShapeEdit(goodsShape);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsShape);
    }

    @PostMapping(value = "/goodsshape")
    public String goodsShapeAdd(@RequestBody GoodsShape goodsShape){
        if (null == goodsShape){
            return null;
        }
        goodsShapeService.goodsShapeAdd(goodsShape);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsShape);
    }

    @DeleteMapping(value = "/goodsshape/{id}")
    public String goodsShapeDelete(@PathVariable("id") Long id){
        GoodsShape goodsShape = goodsShapeService.goodsShapeSelectById(id);
        if (null == goodsShape){
            return null;
        }
        goodsShapeService.setDeleteFlagTrue(goodsShape);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsShape);
    }

    @GetMapping(value = "/goodsshape/file")
    public void goodsShapeFileDownload(HttpServletResponse response, String lang){

        List<GoodsShape> goodsShapes = goodsShapeService.getUsers(false); // 假设有一个GoodsShapeService来获取数据
        Class<GoodsShape> goodsShapeClass = GoodsShape.class;
        Field[] fields = goodsShapeClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsShape goodsShape : goodsShapes){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsShape.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsShape);
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
