package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsMapper;
import org.iwms.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author leung
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public IPage<Goods> getGoodssByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<Goods> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_code", "goods_desc", "goods_supplier", "goods_weight",
                "goods_w", "goods_d", "goods_h", "unit_volume", "goods_unit", "goods_class", "goods_brand",
                "goods_color", "goods_shape", "goods_specs", "goods_origin", "safety_stock", "goods_cost", "goods_price",
                "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsMapper.selectPage(page, queryWrapper);
    }

    public List<Goods> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsMapper.selectByMap(whereMap);
    }


    public Goods goodsSelectById(Long id) {
        return goodsMapper.selectById(id);
    }

    public void goodsEdit(Goods goods) {
        // 如果id不为空，则进行更新操作
        if (goods.getId() != null) {
            goodsMapper.updateById(goods);
        } else {
            // 如果id为空，则进行插入操作
            goodsMapper.insert(goods);
        }
    }

    public void goodsDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(Goods goods) {
        goods.setDelete(true);
        goodsMapper.updateById(goods);
    }

    public void setDeleteFlagFalse(Goods goods) {
        goods.setDelete(false);
        goodsMapper.updateById(goods);
    }

    public void goodsAdd(Goods goods) {
        goods.setCreateTime(new Date());
        goods.setCreater("11");
        goodsMapper.insert(goods);
    }

    public List<Goods> getGoodss(String name, boolean isDelete) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "goods_code", "goods_desc", "goods_supplier", "goods_weight",
                "goods_w", "goods_d", "goods_h", "unit_volume", "goods_unit", "goods_class", "goods_brand",
                "goods_color", "goods_shape", "goods_specs", "goods_origin", "safety_stock", "goods_cost", "goods_price",
                "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_name", name);
        }
        return goodsMapper.selectList(queryWrapper);
    }

    public List<String> availableCodeList() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        List<String> resList = new ArrayList<>();
        queryWrapper.select("id", "goods_code");
        queryWrapper.eq("is_delete", 0);
        List<Goods> goods = goodsMapper.selectList(queryWrapper);
        for (Goods g : goods){
            resList.add(g.getGoodsCode());
        }
        return resList;
    }
}
