package org.iwms.driver.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.driver.constant.Constant;
import org.iwms.driver.model.DispatchDetails;
import org.iwms.driver.model.Driver;
import org.iwms.driver.model.ResponseMsg;
import org.iwms.driver.service.DispatchListService;
import org.iwms.driver.service.DriverService;
import org.iwms.driver.utils.ExportCSVUtil;
import org.iwms.driver.utils.ResponseMsgUtil;
import org.iwms.driver.utils.StringUtil;
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
public class DispatchListController {

    /**
     * 登录成功执行接口
     * @return
     */
    @Autowired
    private DispatchListService dispatchListService;

    @RequestMapping(value = "/dispatchlist")
    public String driverPage(int page, @RequestParam(value = "dn_code__icontains") String code){

        IPage<DispatchDetails> dispatchDetailsByPage = dispatchListService.getByDispatchListsPage(page, code, Constant.DEFAULT_PAGE_SIZE);

        List<DispatchDetails> dispatchDetails = dispatchDetailsByPage.getRecords();

        ResponseMsg<DispatchDetails> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(dispatchDetails.size());
        responseMsg.setResults(dispatchDetails);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }


}
