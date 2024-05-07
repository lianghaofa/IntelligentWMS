package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsClass;
import org.iwms.goods.service.GoodsClassService;
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
public class GoodsClassController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsClassService goodsClassService;

    @RequestMapping(value = "/goodsclass")
    public String goodsClassPage(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "goods_class__icontains", required = false) String name){

        int pageNum = StringUtil.isBlank(page) || "NaN".equals(page)? 1 : Integer.parseInt(page);

        IPage<GoodsClass> usersByPage = goodsClassService.getGoodsClasssByPage(pageNum, name, Constant.DEFAULT_PAGE_SIZE);

        List<GoodsClass> goodsClasss = usersByPage.getRecords();

        ResponseMsg<GoodsClass> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsClasss.size());
        responseMsg.setResults(goodsClasss);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsclass/{id}")
    public String goodsClassEdit(@RequestBody GoodsClass goodsClass, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsClass.setId(id);
        }
        goodsClassService.goodsClassEdit(goodsClass);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsClass);
    }

    @PostMapping(value = "/goodsclass")
    public String goodsClassAdd(@RequestBody GoodsClass goodsClass){
        if (null == goodsClass){
            return null;
        }
        goodsClassService.goodsClassAdd(goodsClass);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsClass);
    }

    @DeleteMapping(value = "/goodsclass/{id}")
    public String goodsClassDelete(@PathVariable("id") Long id){
        GoodsClass goodsClass = goodsClassService.goodsClassSelectById(id);
        if (null == goodsClass){
            return null;
        }
        goodsClassService.setDeleteFlagTrue(goodsClass);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsClass);
    }

    @GetMapping(value = "/goodsclass/file")
    public void goodsClassFileDownload(HttpServletResponse response, String lang){

        List<GoodsClass> goodsClasss = goodsClassService.getGoodsClass(false); // 假设有一个GoodsClassService来获取数据
        Class<GoodsClass> goodsClassClass = GoodsClass.class;
        Field[] fields = goodsClassClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsClass goodsClass : goodsClasss){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsClass.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsClass);
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
