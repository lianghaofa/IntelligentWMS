package org.iwms.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.mapper.WareHouseMapper;
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
public class WareHouseService {

    @Autowired
    private WareHouseMapper wareHouseMapper;


    public IPage<WareHouse> getWareHouseByPage(int pageNum, String name, int pageSize) {
        Page<WareHouse> page = new Page<>(pageNum, pageSize);
        QueryWrapper<WareHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "warehouse_name", "warehouse_city", "warehouse_address", "warehouse_contact", "warehouse_manager", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        return wareHouseMapper.selectPage(page, queryWrapper);
    }

    public void wareHouseEdit(WareHouse wareHouse) {
        // 如果id不为空，则进行更新操作
        if (wareHouse.getId() != null) {
            wareHouseMapper.updateById(wareHouse);
        } else {
            // 如果id为空，则进行插入操作
            wareHouseMapper.insert(wareHouse);
        }
    }

    public void wareHouseAdd(WareHouse wareHouse) {
        wareHouse.setCreateTime(new Date());
        wareHouse.setCreater("11");
        wareHouseMapper.insert(wareHouse);
    }

    public WareHouse wareHouseSelectById(Long id) {
        return wareHouseMapper.selectById(id);
    }

    public void setDeleteFlagTrue(WareHouse wareHouse) {
        wareHouse.setDelete(true);
        wareHouseMapper.updateById(wareHouse);
    }

    public void setDeleteFlagFalse(WareHouse wareHouse) {
        wareHouse.setDelete(false);
        wareHouseMapper.updateById(wareHouse);
    }

    public List<WareHouse> getWareHouses(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return wareHouseMapper.selectByMap(whereMap);
    }
}
