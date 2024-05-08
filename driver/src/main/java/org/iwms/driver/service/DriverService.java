package org.iwms.driver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.iwms.common.core.exception.SystemErrorType;
import org.iwms.common.core.exception.ValidationException;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.iwms.driver.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author leung
 */
@Service
public class DriverService {

    static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    private DriverMapper driverMapper;

//    @Autowired
//    private SqlRunner sqlRunner;


    @Transactional
    public IPage<Driver> getDriversByPage(int pageNum, String name, int pageSize) {
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
        driver.setDelete(true);
        driverMapper.updateById(driver);
    }

    public void setDeleteFlagFalse(Driver driver) {
        driver.setDelete(false);
        driverMapper.updateById(driver);
    }

    public void driverAdd(Driver driver) {
        driver.setCreateTime(new Date());
        driver.setCreater("11");
        driverMapper.insert(driver);
    }


    @Transactional
    public void setDriverAge(Driver driver, int age, String column) {
        String tableName = getTableName(driver.getClass());
        if (StringUtil.isBlank(tableName)){
            throw new ValidationException(SystemErrorType.SYSTEM_BUSY, "table 名称为空。");
        }else {
            String sql = plusDriverAgeSQL(driver, age, column);
            boolean update = SqlRunner.db().update(sql);
            if (!update){
                throw new ValidationException(SystemErrorType.SYSTEM_BUSY, "系统完蛋了。");
            }
        }
    }


    public static String getTableName(Class<?> clazz) {
        TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
        if (tableNameAnnotation != null) {
            return tableNameAnnotation.value();
        } else {
            return null;
        }
    }

    public static String plusDriverAgeSQL(Driver driver, int age, String column) {
        Long id = driver.getId();
        String tableName = getTableName(driver.getClass());
        String sql = "";
        if (age == 0){
            return "";
        }
        if (StringUtil.isBlank(tableName)){
            logger.info("table 名称为空");
        }else {
            String setSrt = column;
            if (age >= 0){
                setSrt += "=" + column + "+" + age;
            }else {
                setSrt += "=" + column + age;
            }
            // 如果是正数，不处理。负数的话要加上约束条件
            String caotrait = "";
            if (age < 0){
                caotrait = " and " + column + ">=" + (-age);
            }
            sql = "update " + tableName + " set " + setSrt + " where id=" + id + caotrait;
        }
        return sql;
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.setId(1L);
        System.out.println(plusDriverAgeSQL(driver, -1, "age"));
    }

    @Transactional
    public void setDriverName(Driver driver, String name) {
        Long id = driver.getId();
        String tableName = getTableName(driver.getClass());
        if (StringUtil.isBlank(tableName)){
            logger.info("table 名称为空");
        }else {
            String sql = "update " + tableName + " set driver_name='" + name + "' where id=" + id;
            SqlRunner.db().update(sql);
        }
    }
}
