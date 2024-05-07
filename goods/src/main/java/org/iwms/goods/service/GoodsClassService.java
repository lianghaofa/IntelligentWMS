package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsClassMapper;
import org.iwms.goods.model.GoodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author leung
 */
@Service
public class GoodsClassService {

    @Autowired
    private GoodsClassMapper goodsClassMapper;

    public IPage<GoodsClass> getGoodsClasssByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsClass> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsClass> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_class", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_class", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsClassMapper.selectPage(page, queryWrapper);
    }


    public List<String> getGoodsClassStringList(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        List<GoodsClass> goodsClasses = getGoodsClass(isDelete);
        List<String> resStr = new ArrayList<>();
        for (GoodsClass goodsClass : goodsClasses) {
            resStr.add(goodsClass.getGoodsClass());
        }
        return resStr;
    }


    public List<GoodsClass> getGoodsClass(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsClassMapper.selectByMap(whereMap);
    }





    public GoodsClass goodsClassSelectById(Long id) {
        return goodsClassMapper.selectById(id);
    }

    public void goodsClassEdit(GoodsClass goodsClass) {
        // 如果id不为空，则进行更新操作
        if (goodsClass.getId() != null) {
            goodsClassMapper.updateById(goodsClass);
        } else {
            // 如果id为空，则进行插入操作
            goodsClassMapper.insert(goodsClass);
        }
    }

    public void goodsClassDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsClassMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsClass goodsClass) {
        goodsClass.setDelete(true);
        goodsClassMapper.updateById(goodsClass);
    }

    public void setDeleteFlagFalse(GoodsClass goodsClass) {
        goodsClass.setDelete(false);
        goodsClassMapper.updateById(goodsClass);
    }

    public void goodsClassAdd(GoodsClass goodsClass) {
        goodsClass.setCreateTime(new Date());
        goodsClass.setCreater("11");
        goodsClassMapper.insert(goodsClass);
    }
}
