package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;

import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.model.Goods;
import org.iwms.goods.model.GoodsClass;
import org.iwms.goods.service.GoodsClassService;
import org.iwms.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.iwms.goods.model.ResponseMsg;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author leung
 */
@RestController
public class GoodsController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsClassService goodsClassService;

    @RequestMapping(value = "/goods")
    public String goodsPage(Integer page, @RequestParam(value = "goods_name__icontains", required = false) String name){
        ResponseMsg<Goods> responseMsg = new ResponseMsg<>();
        List<Goods> goodss = new ArrayList<>();
        if (null == page){
            goodss = goodsService.getGoodss(name, false);

            List<String> goodsClass = goodsClassService.getGoodsClassStringList(false);

            responseMsg.setGoodsClassList(goodsClass);
        }else {
            IPage<Goods> usersByPage = goodsService.getGoodssByPage(page, name, Constant.DEFAULT_PAGE_SIZE);
            goodss = usersByPage.getRecords();
        }
        responseMsg.setCount(goodss.size());
        responseMsg.setResults(goodss);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @GetMapping(value = "/goods/codelist")
    public String availableCodeList(){

        List<String> codeList = goodsService.availableCodeList();
        return ResponseMsgUtil.writeObjectAsJsonString(codeList);
    }

    @PutMapping(value = "/goods/{id}")
    public String goodsEdit(@RequestBody Goods goods, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goods.setId(id);
        }
        goodsService.goodsEdit(goods);
        return ResponseMsgUtil.writeObjectAsJsonString(goods);
    }

    @PostMapping(value = "/goods")
    public String goodsAdd(@RequestBody Goods goods){
        if (null == goods){
            return null;
        }
        goodsService.goodsAdd(goods);
        return ResponseMsgUtil.writeObjectAsJsonString(goods);
    }

    @DeleteMapping(value = "/goods/{id}")
    public String goodsDelete(@PathVariable("id") Long id){
        Goods goods = goodsService.goodsSelectById(id);
        if (null == goods){
            return null;
        }
        goodsService.setDeleteFlagTrue(goods);
        return ResponseMsgUtil.writeObjectAsJsonString(goods);
    }

    @GetMapping(value = "/goods/file")
    public void goodsFileDownload(HttpServletResponse response, String lang){

        List<Goods> goodss = goodsService.getUsers(false); // 假设有一个GoodsService来获取数据
        Class<Goods> goodsClass = Goods.class;
        Field[] fields = goodsClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Goods goods : goodss){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goods.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goods);
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
