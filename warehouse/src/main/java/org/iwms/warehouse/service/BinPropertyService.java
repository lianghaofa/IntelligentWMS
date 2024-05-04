package org.iwms.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.mapper.BinPropertyMapper;
import org.iwms.warehouse.mapper.WareHouseMapper;
import org.iwms.warehouse.model.BinProperty;
import org.iwms.warehouse.model.WareHouse;
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
public class BinPropertyService {

    @Autowired
    private BinPropertyMapper binPropertyMapper;


    public IPage<BinProperty> getBinPropertyByPage(int pageNum, String name, int pageSize) {
        Page<BinProperty> page = new Page<>(pageNum, pageSize);
        QueryWrapper<BinProperty> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "bin_property", "creater", "is_delete", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        return binPropertyMapper.selectPage(page, queryWrapper);
    }

    public void binPropertyEdit(BinProperty binProperty) {
        // 如果id不为空，则进行更新操作
        if (binProperty.getId() != null) {
            binPropertyMapper.updateById(binProperty);
        } else {
            // 如果id为空，则进行插入操作
            binPropertyMapper.insert(binProperty);
        }
    }

    public void binPropertyAdd(BinProperty binProperty) {
        binProperty.setCreateTime(new Date());
        binProperty.setCreater("11");
        binPropertyMapper.insert(binProperty);
    }

    public BinProperty binPropertySelectById(Long id) {
        return binPropertyMapper.selectById(id);
    }

    public void setDeleteFlagTrue(BinProperty binProperty) {
        binProperty.setDelete(true);
        binPropertyMapper.updateById(binProperty);
    }

    public void setDeleteFlagFalse(BinProperty binProperty) {
        binProperty.setDelete(false);
        binPropertyMapper.updateById(binProperty);
    }

    public List<BinProperty> getBinProperties(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return binPropertyMapper.selectByMap(whereMap);
    }
}
