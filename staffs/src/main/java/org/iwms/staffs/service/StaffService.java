package org.iwms.staffs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.staffs.mapper.StaffMapper;
import org.iwms.staffs.model.Staff;
import org.iwms.staffs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leung
 */
@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;

    public IPage<Staff> getUsersByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<Staff> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "staff_name", "staff_type", "check_code", "create_time", "update_time", "error_check_code_counter", "is_lock", "inactive");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return staffMapper.selectPage(page, queryWrapper);
    }

    public IPage<Staff> getStaffsByPage(int pageNum, String name,int defaultPageSize) {

        Page<Staff> page = new Page<>(pageNum, defaultPageSize);
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "staff_name", "staff_type", "check_code", "create_time", "update_time", "error_check_code_counter", "is_lock" , "inactive");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("staff_name", name);
        }
        return staffMapper.selectPage(page, queryWrapper);
    }

    public void staffAdd(Staff staff) {

        staff.setCreateTime(new Date());
        staff.setCreater("11");
        staffMapper.insert(staff);

    }

    public Staff staffLock(Long id) {
        Staff staff = staffMapper.selectById(id);
        if (null != staff){
            staff.setLocks(true);
            staffMapper.updateById(staff);
        }
        return staff;
    }

    public void staffEdit(Staff staff) {
        staffMapper.updateById(staff);
    }

    public void updateStaffNameAndTypeAndLock(Long id, String staffName, String staffType, boolean locks) {
        UpdateWrapper<Staff> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
                .set("staff_name", staffName)
                .set("inactive", locks)
                .set("staff_type", staffType);
        staffMapper.update(null, updateWrapper);

    }

    public void staffDelete(Long id) {
        staffMapper.deleteById(id);
    }

    public Staff getById(Long id) {
        Staff staff = staffMapper.selectById(id);
        return staff;
    }

    public List<Staff> getStaffs(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return staffMapper.selectByMap(whereMap);
    }
}
