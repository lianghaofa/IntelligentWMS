package org.iwms.stock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.stock.mapper.StockBinMapper;
import org.iwms.stock.mapper.StockMapper;
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
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockBinMapper stockBinMapper;

    public IPage<Stock> getStocksByPage(int pageNum, String name, int pageSize) {
        Page<Stock> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "goods_code", "goods_desc", "goods_qty", "onhand_stock", "can_order_stock", "ordered_stock",
                "inspect_stock", "hold_stock", "damage_stock", "asn_stock", "dn_stock", "pre_load_stock",
                "pre_sort_stock", "sorted_stock", "pick_stock", "picked_stock", "back_order_stock",
                "create_time", "update_time", "creater");
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        return stockMapper.selectPage(page, queryWrapper);
    }

    public List<Stock> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return stockMapper.selectByMap(whereMap);
    }


    public Stock stockSelectById(Long id) {
        return stockMapper.selectById(id);
    }

    public void stockEdit(Stock stock) {
        // 如果id不为空，则进行更新操作
        if (stock.getId() != null) {
            stockMapper.updateById(stock);
        } else {
            // 如果id为空，则进行插入操作
            stockMapper.insert(stock);
        }
    }

    public void stockDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            stockMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(Stock stock) {
        // stock.setDelete(true);
        stockMapper.updateById(stock);
    }

    public void setDeleteFlagFalse(Stock stock) {
        // stock.setDelete(false);
        stockMapper.updateById(stock);
    }

    public void stockAdd(Stock stock) {
        stock.setCreateTime(new Date());
        // stock.setCreater("11");
        stockMapper.insert(stock);
    }

    public IPage<StockBin> getBinsByPage(int pageNum, String name, boolean emptyLabel,int pageSize) {

        Page<StockBin> page = new Page<>(pageNum, pageSize);

        QueryWrapper<StockBin> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "bin_name", "goods_code", "goods_qty", "pick_qty", "picked_qty", "bin_size",
                "bin_property", "t_code", "create_time", "update_time", "creater");
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("bin_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return stockBinMapper.selectPage(page, queryWrapper);
    }
}
