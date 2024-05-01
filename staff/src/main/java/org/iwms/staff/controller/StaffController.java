package org.iwms.staff.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.iwms.staff.constant.Constant;
import org.iwms.staff.model.ResponseMsg;
import org.iwms.staff.model.Staff;
import org.iwms.staff.model.StaffType;
import org.iwms.staff.service.StaffService;
import org.iwms.staff.service.StaffTypeService;
import org.iwms.staff.utils.ExportCSVUtil;
import org.iwms.staff.utils.ResponseMsgUtil;
import org.iwms.staff.utils.StringUtil;
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
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffTypeService staffTypeService;

    @RequestMapping(value = "/staff")
    public String staffPage(int page, @RequestParam(value = "staff_name__icontains", required = false) String name){

        IPage<Staff> staffsByPage = staffService.getStaffsByPage(page, name, Constant.DEFAULT_PAGE_SIZE);
        List<Staff> staffs = staffsByPage.getRecords();
        ResponseMsg<Staff> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(staffs.size());
        responseMsg.setResults(staffs);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }

    @PutMapping(value = "/staff/{id}")
    public String staffLock(@RequestBody Staff staff , @PathVariable("id") Long id){

        if (null == id){
            return null;
        }
        staffService.updateStaffNameAndTypeAndLock(id, staff.getStaffName(), staff.getStaffType(), staff.getInactive());
        return null;
    }

    @DeleteMapping(value = "/staff/{id}")
    public String staffDelete(@PathVariable("id") Long id){

        if (null == id){
            return null;
        }
        staffService.staffDelete(id);
        // taffService.updateStaffNameAndTypeAndLock(id, staff.getStaffName(), staff.getStaffType(), staff.getLocks());

        return null;
    }

    @RequestMapping(value = "/staff/type")
    public String driverPage(int page){

        IPage<StaffType> usersByPage = staffTypeService.getStaffTypeByPage(page, Constant.DEFAULT_PAGE_SIZE);

        //提示具体用户名称登录成功
        List<StaffType> staffTypes = usersByPage.getRecords();

        ResponseMsg<StaffType> responseMsg = new ResponseMsg<>();
        responseMsg.setCount(staffTypes.size());
        responseMsg.setResults(staffTypes);
        return ResponseMsgUtil.writeObjectAsJsonString(responseMsg);
    }


    @PostMapping(value = "/staff")
    public String driverAdd(@RequestBody Staff staff){
        if (null == staff){
            return null;
        }
        staffService.staffAdd(staff);
        return ResponseMsgUtil.writeObjectAsJsonString(staff);
    }

    @GetMapping(value = "/staff/file")
    public void driverFileDownload(HttpServletResponse response, String lang){

        List<Staff> staffs = staffService.getStaffs(false); // 假设有一个DriverService来获取数据


        Class<Staff> driverClass = Staff.class;
        Field[] fields = driverClass.getDeclaredFields();
        List<String> tableHeaderList = new ArrayList<>();
        for (Field field : fields){
            tableHeaderList.add(field.getName());
        }
        List<Object[]> cellList = new ArrayList<>();
        for (Staff staff : staffs){
            List<Object> objects = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterMethodName = "get" + StringUtil.capitalizeFirst(fieldName); // 获取getter方法名
                Object value = null;
                try {
                    Method getterMethod = staff.getClass().getMethod(getterMethodName);
                    value = getterMethod.invoke(staff);
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
