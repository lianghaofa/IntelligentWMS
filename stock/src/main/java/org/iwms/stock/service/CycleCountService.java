package org.iwms.stock.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.stock.mapper.CycleCountDayMapper;
import org.iwms.stock.mapper.ManualCycleCountMapper;
import org.iwms.stock.mapper.StockBinMapper;
import org.iwms.stock.mapper.StockMapper;
import org.iwms.stock.model.CycleCountDay;
import org.iwms.stock.model.ManualCycleCount;
import org.iwms.stock.model.Stock;
import org.iwms.stock.model.StockBin;
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
public class CycleCountService {

    @Autowired
    private CycleCountDayMapper cycleCountDayMapper;

    @Autowired
    private ManualCycleCountMapper manualCycleCountMapper;

    public IPage<CycleCountDay> getCycleCountDaysByPage(Integer pageNum, String name, int pageSize) {


        Page<CycleCountDay> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize);

        QueryWrapper<CycleCountDay> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "cyclecount_status", "bin_name", "goods_code", "physical_inventory", "difference", "t_code",
                "create_time", "update_time", "creater");

        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        return cycleCountDayMapper.selectPage(page, queryWrapper);
    }

    public IPage<ManualCycleCount> getManualCycleCountsByPage(int pageNum, String name, int pageSize) {
        Page<ManualCycleCount> page = new Page<>(pageNum, pageSize);

        QueryWrapper<ManualCycleCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "cyclecount_status", "bin_name", "goods_code", "goods_qty", "physical_inventory", "difference", "t_code",
                "create_time", "update_time", "creater");

        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        return manualCycleCountMapper.selectPage(page, queryWrapper);
    }

    public IPage<ManualCycleCount> getManualCyclecountrecOrdersByPage(int pageNum, String name, int defaultPageSize) {

        Page<ManualCycleCount> page = new Page<>(pageNum, defaultPageSize);

        QueryWrapper<ManualCycleCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "cyclecount_status", "bin_name", "goods_code", "goods_qty", "physical_inventory", "difference", "t_code",
                "create_time", "update_time", "creater");

        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        queryWrapper.eq("cyclecount_status", 1);
        return manualCycleCountMapper.selectPage(page, queryWrapper);
    }
}
