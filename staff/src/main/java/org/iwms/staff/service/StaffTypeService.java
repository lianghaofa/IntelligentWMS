package org.iwms.staff.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.staff.mapper.StaffMapper;
import org.iwms.staff.mapper.StaffTypeMapper;
import org.iwms.staff.model.Staff;
import org.iwms.staff.model.StaffType;
import org.iwms.staff.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leung
 */
@Service
public class StaffTypeService {

    @Autowired
    private StaffTypeMapper staffTypeMapper;

    public IPage<StaffType> getStaffTypeByPage(int pageNum, int pageSize) {
        // 创建分页对象
        Page<StaffType> page = new Page<>(pageNum, pageSize);

        QueryWrapper<StaffType> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "staff_type", "creater", "create_time", "update_time");
        queryWrapper.eq("is_delete", 0);
        // 调用 MyBatis-Plus 的分页查询方法
        return staffTypeMapper.selectPage(page, queryWrapper);
    }

}
