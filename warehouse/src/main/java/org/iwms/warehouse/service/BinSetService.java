package org.iwms.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.mapper.BinSetMapper;
import org.iwms.warehouse.model.BinSet;
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
public class BinSetService {

    @Autowired
    private BinSetMapper binSetMapper;


    public IPage<BinSet> getBinSetByPage(int pageNum, String name, int pageSize) {
        Page<BinSet> page = new Page<>(pageNum, pageSize);
        QueryWrapper<BinSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "bin_size", "bin_name", "bin_property", "empty_label", "bar_code", "creater", "is_delete", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        return binSetMapper.selectPage(page, queryWrapper);
    }

    public void binSetEdit(BinSet binSet) {
        // 如果id不为空，则进行更新操作
        if (binSet.getId() != null) {
            binSetMapper.updateById(binSet);
        } else {
            // 如果id为空，则进行插入操作
            binSetMapper.insert(binSet);
        }
    }

    public void binSetAdd(BinSet binSet) {
        binSet.setCreateTime(new Date());
        binSet.setCreater("11");
        binSetMapper.insert(binSet);
    }

    public BinSet binSetSelectById(Long id) {
        return binSetMapper.selectById(id);
    }

    public void setDeleteFlagTrue(BinSet binSet) {
        binSet.setDelete(true);
        binSetMapper.updateById(binSet);
    }

    public void setDeleteFlagFalse(BinSet binSet) {
        binSet.setDelete(false);
        binSetMapper.updateById(binSet);
    }

    public List<BinSet> getBinProperties(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return binSetMapper.selectByMap(whereMap);
    }
}
