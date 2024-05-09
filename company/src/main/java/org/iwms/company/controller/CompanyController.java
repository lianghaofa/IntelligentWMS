package org.iwms.company.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.common.core.constant.Constant;
import org.iwms.common.core.utils.ExportCSVUtil;
import org.iwms.common.core.utils.ResponseMsgUtil;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.common.web.model.ResponseMsg;
import org.iwms.company.model.Company;
import org.iwms.company.service.CompanyService;
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
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/company")
    public String companyPage(int page, @RequestParam(value = "company_name__icontains", required = false) String name){

        IPage<Company> usersByPage = companyService.getCompanyByPage(page, name, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<Company> drivers = usersByPage.getRecords();

        ResponseMsg<Company> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(drivers.size());
        responseMsg.setResults(drivers);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/company/{id}")
    public String companyEdit(@RequestBody Company company, @PathVariable("id") Long id){

        if (null == id){
            return null;
        }else {
            company.setId(id);
        }
        companyService.companyEdit(company);
        return ResponseMsgUtil.writeObjectAsJsonString(company);
    }

    @PostMapping(value = "/company")
    public String companyAdd(@RequestBody Company company){
        if (null == company){
            return null;
        }
        companyService.companyAdd(company);
        return ResponseMsgUtil.writeObjectAsJsonString(company);
    }

    @DeleteMapping(value = "/company/{id}")
    public String companyDelete(@PathVariable("id") Long id){
        Company company = companyService.companySelectById(id);
        if (null == company){
            return null;
        }
        companyService.setDeleteFlagTrue(company);
        return ResponseMsgUtil.writeObjectAsJsonString(company);
    }

    @GetMapping(value = "/company/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Company> companys = companyService.getCompanys(false); // 假设有一个DriverService来获取数据
        Class<Company> driverClass = Company.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Company company : companys){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = company.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(company);
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
