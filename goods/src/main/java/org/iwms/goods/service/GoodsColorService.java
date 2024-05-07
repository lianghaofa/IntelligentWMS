package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsColorMapper;
import org.iwms.goods.model.GoodsColor;
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
public class GoodsColorService {

    @Autowired
    private GoodsColorMapper goodsColorMapper;

    public IPage<GoodsColor> getGoodsColorsByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsColor> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsColor> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_color", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_color", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsColorMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsColor> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsColorMapper.selectByMap(whereMap);
    }


    public GoodsColor goodsColorSelectById(Long id) {
        return goodsColorMapper.selectById(id);
    }

    public void goodsColorEdit(GoodsColor goodsColor) {
        // 如果id不为空，则进行更新操作
        if (goodsColor.getId() != null) {
            goodsColorMapper.updateById(goodsColor);
        } else {
            // 如果id为空，则进行插入操作
            goodsColorMapper.insert(goodsColor);
        }
    }

    public void goodsColorDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsColorMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsColor goodsColor) {
        goodsColor.setDelete(true);
        goodsColorMapper.updateById(goodsColor);
    }

    public void setDeleteFlagFalse(GoodsColor goodsColor) {
        goodsColor.setDelete(false);
        goodsColorMapper.updateById(goodsColor);
    }

    public void goodsColorAdd(GoodsColor goodsColor) {
        goodsColor.setCreateTime(new Date());
        goodsColor.setCreater("11");
        goodsColorMapper.insert(goodsColor);
    }
}
