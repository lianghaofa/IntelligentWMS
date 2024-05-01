package org.iwms.driver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.iwms.driver.utils.StringUtil;
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
public class DriverService {

    @Autowired
    private DriverMapper driverMapper;

    public IPage<Driver> getUsersByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<Driver> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "driver_name", "license_plate", "contact", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return driverMapper.selectPage(page, queryWrapper);
    }

    public List<Driver> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return driverMapper.selectByMap(whereMap);
    }


    public Driver driverSelectById(Long id) {
        return driverMapper.selectById(id);
    }

    public void driverEdit(Driver driver) {
        // 如果id不为空，则进行更新操作
        if (driver.getId() != null) {
            driverMapper.updateById(driver);
        } else {
            // 如果id为空，则进行插入操作
            driverMapper.insert(driver);
        }
    }

    public void driverDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            driverMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(Driver driver) {
        driver.setIsDelete(true);
        driverMapper.updateById(driver);
    }

    public void setDeleteFlagFalse(Driver driver) {
        driver.setIsDelete(false);
        driverMapper.updateById(driver);
    }

    public void driverAdd(Driver driver) {
        driver.setCreateTime(new Date());
        driver.setCreater("11");
        driverMapper.insert(driver);
    }
}
