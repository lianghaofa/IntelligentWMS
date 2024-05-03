package org.iwms.stock.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.stock.model.CycleCountDay;
import org.iwms.stock.model.ManualCycleCount;
import org.iwms.stock.model.Stock;
import org.iwms.stock.service.CycleCountService;
import org.iwms.stock.service.StockService;
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
public class CycleCountController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private CycleCountService cycleCountService;

    @RequestMapping(value = "/cyclecount")
    public String cyclecountPage(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "ordering", required = false) String ordering,
                            @RequestParam(value = "stock_name__icontains", required = false) String name){

        IPage<CycleCountDay> usersByPage = cycleCountService.getCycleCountDaysByPage(page == null ? 1 : page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<CycleCountDay> stocks = usersByPage.getRecords();

        ResponseMsg<CycleCountDay> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(stocks.size());
        responseMsg.setResults(stocks);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @RequestMapping(value = "/cyclecount/manualcyclecount")
    public String manualcyclecountPage(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "ordering", required = false) String ordering,
                            @RequestParam(value = "stock_name__icontains", required = false) String name){

        IPage<ManualCycleCount> usersByPage = cycleCountService.getManualCycleCountsByPage(page == null ? 1 : page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<ManualCycleCount> stocks = usersByPage.getRecords();

        ResponseMsg<ManualCycleCount> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(stocks.size());
        responseMsg.setResults(stocks);
        return ResponseMsgUtil.writeObjectAsJsonString(stocks);
    }
    @RequestMapping(value = "/cyclecount/manualcyclecountrecorder")
    public String manualcyclecountrecorderPage(@RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "ordering", required = false) String ordering,
                                       @RequestParam(value = "stock_name__icontains", required = false) String name){



        IPage<ManualCycleCount> usersByPage = cycleCountService.getManualCyclecountrecOrdersByPage(
                page == null || "NaN".equals(page)? 1 : Integer.parseInt(page), name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<ManualCycleCount> stocks = usersByPage.getRecords();

        ResponseMsg<ManualCycleCount> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(stocks.size());
        responseMsg.setResults(stocks);
        return ResponseMsgUtil.writeObjectAsJsonString(stocks);
    }



//    @GetMapping(value = "/stock/file")
//    public void stockFileDownload(HttpServletResponse response, String lang){
//
//        List<Stock> stocks = stockService.getUsers(false); // 假设有一个StockService来获取数据
//        Class<Stock> stockClass = Stock.class;
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
//    }

}
