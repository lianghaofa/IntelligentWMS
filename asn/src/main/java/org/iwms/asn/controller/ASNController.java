package org.iwms.asn.controller;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.asn.feign.ProviderClient;
import org.iwms.asn.model.*;
import org.iwms.asn.service.ASNService;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author leung
 */
@RestController
@RequestMapping(value = "/asn")
public class ASNController {

    static final Logger logger = LoggerFactory.getLogger(ASNController.class);

    @Autowired
    private ASNService asnService;

    @Autowired
    private ProviderClient providerClient;

    @RequestMapping(value = "/list")
    public String asnPage(@RequestParam(value = "page", required = false) String page,
                            @RequestParam(value = "ordering", required = false) String ordering,
                            @RequestParam(value = "stock_name__icontains", required = false) String name){


        int pageNum = StringUtil.isBlank(page) || "NaN".equals(page)? 1 : Integer.parseInt(page);

        IPage<ASNList> usersByPage = asnService.getASNsByPage(pageNum, name, Constant.DEFAULT_PAGE_SIZE);
        List<ASNList> stocks = usersByPage.getRecords();

        ASNListResponseMsg<ASNList> responseMsg = new ASNListResponseMsg<>();
        responseMsg.setCount(stocks.size());

        List<String> list = providerClient.list();

        responseMsg.setSupplierList(list);
        for (ASNList stock : stocks) {
            stock.setBarCode("123");
            // stock.setIsDelete(false);
            stock.setOpenid("124");
            TransportationFee transportationFee = new TransportationFee();
            transportationFee.setWeightFee(123d);
            List<TransportationFee> transportationFees = new ArrayList<>();
            transportationFees.add(transportationFee);
            stock.setTransportationFee(transportationFees);
        }
        responseMsg.setResults(stocks);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }


//    @PostMapping(value = "/detail")
//    public String asnDetailsCreate(@RequestParam(value = "asn_code", required = false) String asnCode,
//                              @RequestParam(value = "creater", required = false) String creater,
//                              @RequestParam(value = "goods_code", required = false) List<String> goodsCode,
//                              @RequestParam(value = "goods_qty", required = false) List<Double> goodsQty,
//                              @RequestParam(value = "supplier", required = false) String supplier){
//
//        logger.info("asnCode={}, creater={}, goodsCode={}, goodsQty={}, supplier={}", asnCode, creater, goodsCode, goodsQty, supplier);
//        asnService.createByDetails(asnCode, creater, goodsCode, goodsQty, supplier);
//        return ResponseMsgUtil.writeObjectAsJsonString(null);
//    }

    @PostMapping(value = "/detail")
    public String asnDetailsCreate(@RequestBody AsnDetailRequestBody requestBody){

        String asnCode = requestBody.getAsnCode();
        String creater = requestBody.getCreater();
        List<String> goodsCode = requestBody.getGoodsCode();
        List<Double> goodsQty = requestBody.getGoodsQty();
        String supplier = requestBody.getSupplier();

        logger.info("asnCode={}, creater={}, goodsCode={}, goodsQty={}, supplier={}", asnCode, creater, goodsCode, goodsQty, supplier);
        asnService.createByDetails(asnCode, creater, goodsCode, goodsQty, supplier);
        return ResponseMsgUtil.writeObjectAsJsonString(new HashMap<String, String>() {{ put("detail", "success"); }});
    }

    @GetMapping(value = "/viewprint/{id}")
    public String asnViewPrint(@PathVariable(value = "id", required = false) Integer id){
        //
        ASNDetail asnDetail = asnService.getASNDetail(id);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("asn_detail", "aa");

        resultMap.put("supplier_detail", "bbb");

        resultMap.put("warehouse_detail", "ccc");
        logger.info("id={}", id);
        return ResponseMsgUtil.writeObjectAsJsonString(resultMap);
    }


    @GetMapping(value = "/stock/file")
    public void stockFileDownload(HttpServletResponse response, String lang){

//        List<ASNDetail> stocks = asnService.getΩ(false); // 假设有一个StockService来获取数据
//        Class<ASNDetail> stockClass = ASNDetail.class;
//        Field[] fields = stockClass.getDeclaredFields();
//        List<String> tableHeaderList = new ArrayList<>();
//        for (Field field : fields){
//            tableHeaderList.add(field.getName());
//        }
//        List<Object[]> cellList = new ArrayList<>();
//        for (Stock stock : stocks){
//            List<Object> objects = new ArrayList<>();
//            for (Field field : fields) {
//                String fieldName = field.getName();
//                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
//                Object value = null;
//                try {
//                    Method getterMethod = stock.getClass().getMethod(getterMethodName);
//                    value = getterMethod.invoke(stock);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    objects.add(value);
//                }
//            }
//            cellList.add(objects.toArray(new Object[objects.size()]));
//        }
//        String[] tableHeaderArr = tableHeaderList.toArray(new String[tableHeaderList.size()]);
//        String fileName = "导出文件.csv";
//        byte[] bytes = ExportCSVUtil.writeCsvAfterToBytes(tableHeaderArr, cellList);
//        ExportCSVUtil.responseSetProperties(fileName,bytes, response);
    }

}
