package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsShapeMapper;
import org.iwms.goods.model.GoodsShape;
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
public class GoodsShapeService {

    @Autowired
    private GoodsShapeMapper goodsShapeMapper;

    public IPage<GoodsShape> getGoodsShapesByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsShape> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsShape> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_shape", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_shape", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsShapeMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsShape> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsShapeMapper.selectByMap(whereMap);
    }


    public GoodsShape goodsShapeSelectById(Long id) {
        return goodsShapeMapper.selectById(id);
    }

    public void goodsShapeEdit(GoodsShape goodsShape) {
        // 如果id不为空，则进行更新操作
        if (goodsShape.getId() != null) {
            goodsShapeMapper.updateById(goodsShape);
        } else {
            // 如果id为空，则进行插入操作
            goodsShapeMapper.insert(goodsShape);
        }
    }

    public void goodsShapeDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsShapeMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsShape goodsShape) {
        goodsShape.setDelete(true);
        goodsShapeMapper.updateById(goodsShape);
    }

    public void setDeleteFlagFalse(GoodsShape goodsShape) {
        goodsShape.setDelete(false);
        goodsShapeMapper.updateById(goodsShape);
    }

    public void goodsShapeAdd(GoodsShape goodsShape) {
        goodsShape.setCreateTime(new Date());
        goodsShape.setCreater("11");
        goodsShapeMapper.insert(goodsShape);
    }
}
