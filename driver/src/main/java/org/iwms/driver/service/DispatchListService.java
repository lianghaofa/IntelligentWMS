package org.iwms.driver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.driver.mapper.DispatchListMapper;
import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.DispatchDetails;
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
public class DispatchListService {

    @Autowired
    private DispatchListMapper dispatchListMapper;

    public IPage<DispatchDetails> getByDispatchListsPage(int pageNum, String code, int pageSize) {
        // 创建分页对象
        Page<DispatchDetails> page = new Page<>(pageNum, pageSize);
        QueryWrapper<DispatchDetails> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "driver_name", "dn_code", "contact", "creater", "create_time", "update_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(code)){
            queryWrapper.like("dn_code", code);
        }
        return dispatchListMapper.selectPage(page, queryWrapper);
    }

}
