package org.iwms.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.warehouse.mapper.BinSizeMapper;
import org.iwms.warehouse.model.BinSize;
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
public class BinSizeService {

    @Autowired
    private BinSizeMapper binSizeMapper;


    public IPage<BinSize> getBinSizeByPage(int pageNum, String name, int pageSize) {
        Page<BinSize> page = new Page<>(pageNum, pageSize);
        QueryWrapper<BinSize> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "bin_size", "bin_size_w", "bin_size_d", "bin_size_h", "creater", "is_delete", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        return binSizeMapper.selectPage(page, queryWrapper);
    }

    public void binSizeEdit(BinSize binSize) {
        // 如果id不为空，则进行更新操作
        if (binSize.getId() != null) {
            binSizeMapper.updateById(binSize);
        } else {
            // 如果id为空，则进行插入操作
            binSizeMapper.insert(binSize);
        }
    }

    public void binSizeAdd(BinSize binSize) {
        binSize.setCreateTime(new Date());
        binSize.setCreater("11");
        binSizeMapper.insert(binSize);
    }

    public BinSize binSizeSelectById(Long id) {
        return binSizeMapper.selectById(id);
    }

    public void setDeleteFlagTrue(BinSize binSize) {
        binSize.setDelete(true);
        binSizeMapper.updateById(binSize);
    }

    public void setDeleteFlagFalse(BinSize binSize) {
        binSize.setDelete(false);
        binSizeMapper.updateById(binSize);
    }

    public List<BinSize> getBinSizes(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return binSizeMapper.selectByMap(whereMap);
    }
}
