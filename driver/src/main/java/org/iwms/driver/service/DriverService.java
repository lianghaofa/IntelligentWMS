package org.iwms.driver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leung
 */
@Service
public class DriverService {

    @Autowired
    private DriverMapper driverMapper;

    public IPage<Driver> getUsersByPage(int pageNum, int pageSize) {
        // 创建分页对象
        Page<Driver> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "driver_name", "license_plate", "contact", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        // 调用 MyBatis-Plus 的分页查询方法
        return driverMapper.selectPage(page, queryWrapper);
    }
}
