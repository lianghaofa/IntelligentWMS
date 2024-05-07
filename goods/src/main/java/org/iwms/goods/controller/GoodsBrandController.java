package org.iwms.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.goods.model.GoodsBrand;
import org.iwms.goods.service.GoodsBrandService;
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
public class GoodsBrandController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private GoodsBrandService goodsBrandService;

    @RequestMapping(value = "/goodsbrand")
    public String goodsBrandPage(int page, @RequestParam(value = "goodsBrand_name__icontains", required = false) String name){

        IPage<GoodsBrand> usersByPage = goodsBrandService.getGoodsBrandsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<GoodsBrand> goodsBrands = usersByPage.getRecords();

        ResponseMsg<GoodsBrand> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(goodsBrands.size());
        responseMsg.setResults(goodsBrands);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/goodsbrand/{id}")
    public String goodsBrandEdit(@RequestBody GoodsBrand goodsBrand, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            goodsBrand.setId(id);
        }
        goodsBrandService.goodsBrandEdit(goodsBrand);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsBrand);
    }

    @PostMapping(value = "/goodsbrand")
    public String goodsBrandAdd(@RequestBody GoodsBrand goodsBrand){
        if (null == goodsBrand){
            return null;
        }
        goodsBrandService.goodsBrandAdd(goodsBrand);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsBrand);
    }

    @DeleteMapping(value = "/goodsbrand/{id}")
    public String goodsBrandDelete(@PathVariable("id") Long id){
        GoodsBrand goodsBrand = goodsBrandService.goodsBrandSelectById(id);
        if (null == goodsBrand){
            return null;
        }
        goodsBrandService.setDeleteFlagTrue(goodsBrand);
        return ResponseMsgUtil.writeObjectAsJsonString(goodsBrand);
    }

    @GetMapping(value = "/goodsbrand/file")
    public void goodsBrandFileDownload(HttpServletResponse response, String lang){

        List<GoodsBrand> goodsBrands = goodsBrandService.getUsers(false); // 假设有一个GoodsBrandService来获取数据
        Class<GoodsBrand> goodsBrandClass = GoodsBrand.class;
        Field[] fields = goodsBrandClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (GoodsBrand goodsBrand : goodsBrands){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = goodsBrand.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(goodsBrand);
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
