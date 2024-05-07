package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsColor;
import org.iwms.goods.service.GoodsColorService;
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
public class GoodsColorController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsColorService goodsColorService;

    @RequestMapping(value = "/goodscolor")
    public String goodsColorPage(int page, @RequestParam(value = "goods_color__icontains", required = false) String name){

        IPage<GoodsColor> usersByPage = goodsColorService.getGoodsColorsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsColor> goodsColors = usersByPage.getRecords();

        ResponseMsg<GoodsColor> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsColors.size());
        responseMsg.setResults(goodsColors);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodscolor/{id}")
    public String goodsColorEdit(@RequestBody GoodsColor goodsColor, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsColor.setId(id);
        }
        goodsColorService.goodsColorEdit(goodsColor);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsColor);
    }

    @PostMapping(value = "/goodscolor")
    public String goodsColorAdd(@RequestBody GoodsColor goodsColor){
        if (null == goodsColor){
            return null;
        }
        goodsColorService.goodsColorAdd(goodsColor);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsColor);
    }

    @DeleteMapping(value = "/goodscolor/{id}")
    public String goodsColorDelete(@PathVariable("id") Long id){
        GoodsColor goodsColor = goodsColorService.goodsColorSelectById(id);
        if (null == goodsColor){
            return null;
        }
        goodsColorService.setDeleteFlagTrue(goodsColor);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsColor);
    }

    @GetMapping(value = "/goodscolor/file")
    public void goodsColorFileDownload(HttpServletResponse response, String lang){

        List<GoodsColor> goodsColors = goodsColorService.getUsers(false); // 假设有一个GoodsColorService来获取数据
        Class<GoodsColor> goodsColorClass = GoodsColor.class;
        Field[] fields = goodsColorClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsColor goodsColor : goodsColors){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsColor.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsColor);
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
