package org.iwms.stock.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.stock.model.Stock;
import org.iwms.stock.model.StockBin;
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
public class StockBinController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/binset")
    public String stockPage(int page,
                            @RequestParam(value = "empty_label", required = false) Boolean emptyLabel,
                            @RequestParam(value = "bin_name__icontains", required = false) String name){

        IPage<StockBin> usersByPage = stockService.getBinsByPage(page, name,  emptyLabel, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<StockBin> stocks = usersByPage.getRecords();

        ResponseMsg<StockBin> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(stocks.size());
        responseMsg.setResults(stocks);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }
    

}
