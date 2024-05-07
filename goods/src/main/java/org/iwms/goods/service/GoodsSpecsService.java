package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsSpecsMapper;
import org.iwms.goods.model.GoodsSpecs;
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
public class GoodsSpecsService {

    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    public IPage<GoodsSpecs> getGoodsSpecssByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsSpecs> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsSpecs> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_specs", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_specs", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsSpecsMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsSpecs> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsSpecsMapper.selectByMap(whereMap);
    }


    public GoodsSpecs goodsSpecsSelectById(Long id) {
        return goodsSpecsMapper.selectById(id);
    }

    public void goodsSpecsEdit(GoodsSpecs goodsSpecs) {
        // 如果id不为空，则进行更新操作
        if (goodsSpecs.getId() != null) {
            goodsSpecsMapper.updateById(goodsSpecs);
        } else {
            // 如果id为空，则进行插入操作
            goodsSpecsMapper.insert(goodsSpecs);
        }
    }

    public void goodsSpecsDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsSpecsMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsSpecs goodsSpecs) {
        goodsSpecs.setDelete(true);
        goodsSpecsMapper.updateById(goodsSpecs);
    }

    public void setDeleteFlagFalse(GoodsSpecs goodsSpecs) {
        goodsSpecs.setDelete(false);
        goodsSpecsMapper.updateById(goodsSpecs);
    }

    public void goodsSpecsAdd(GoodsSpecs goodsSpecs) {
        goodsSpecs.setCreateTime(new Date());
        goodsSpecs.setCreater("11");
        goodsSpecsMapper.insert(goodsSpecs);
    }
}
