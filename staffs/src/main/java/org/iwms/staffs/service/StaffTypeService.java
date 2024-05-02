package org.iwms.staffs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.staffs.mapper.StaffTypeMapper;
import org.iwms.staffs.model.StaffType;
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
