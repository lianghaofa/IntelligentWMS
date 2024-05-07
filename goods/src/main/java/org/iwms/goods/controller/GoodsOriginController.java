package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsOrigin;
import org.iwms.goods.service.GoodsOriginService;
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
public class GoodsOriginController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsOriginService goodsOriginService;

    @RequestMapping(value = "/goodsorigin")
    public String goodsOriginPage(int page, @RequestParam(value = "goodsOrigin_name__icontains", required = false) String name){

        IPage<GoodsOrigin> usersByPage = goodsOriginService.getGoodsOriginsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsOrigin> goodsOrigins = usersByPage.getRecords();

        ResponseMsg<GoodsOrigin> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsOrigins.size());
        responseMsg.setResults(goodsOrigins);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsorigin/{id}")
    public String goodsOriginEdit(@RequestBody GoodsOrigin goodsOrigin, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsOrigin.setId(id);
        }
        goodsOriginService.goodsOriginEdit(goodsOrigin);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsOrigin);
    }

    @PostMapping(value = "/goodsorigin")
    public String goodsOriginAdd(@RequestBody GoodsOrigin goodsOrigin){
        if (null == goodsOrigin){
            return null;
        }
        goodsOriginService.goodsOriginAdd(goodsOrigin);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsOrigin);
    }

    @DeleteMapping(value = "/goodsorigin/{id}")
    public String goodsOriginDelete(@PathVariable("id") Long id){
        GoodsOrigin goodsOrigin = goodsOriginService.goodsOriginSelectById(id);
        if (null == goodsOrigin){
            return null;
        }
        goodsOriginService.setDeleteFlagTrue(goodsOrigin);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsOrigin);
    }

    @GetMapping(value = "/goodsorigin/file")
    public void goodsOriginFileDownload(HttpServletResponse response, String lang){

        List<GoodsOrigin> goodsOrigins = goodsOriginService.getUsers(false); // 假设有一个GoodsOriginService来获取数据
        Class<GoodsOrigin> goodsOriginClass = GoodsOrigin.class;
        Field[] fields = goodsOriginClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsOrigin goodsOrigin : goodsOrigins){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsOrigin.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsOrigin);
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
