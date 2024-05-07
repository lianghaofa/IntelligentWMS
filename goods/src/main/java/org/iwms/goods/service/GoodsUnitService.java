package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsUnitMapper;
import org.iwms.goods.model.GoodsUnit;
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
public class GoodsUnitService {

    @Autowired
    private GoodsUnitMapper goodsUnitMapper;

    public IPage<GoodsUnit> getGoodsUnitsByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsUnit> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsUnit> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_unit", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_unit", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsUnitMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsUnit> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsUnitMapper.selectByMap(whereMap);
    }


    public GoodsUnit goodsUnitSelectById(Long id) {
        return goodsUnitMapper.selectById(id);
    }

    public void goodsUnitEdit(GoodsUnit goodsUnit) {
        // 如果id不为空，则进行更新操作
        if (goodsUnit.getId() != null) {
            goodsUnitMapper.updateById(goodsUnit);
        } else {
            // 如果id为空，则进行插入操作
            goodsUnitMapper.insert(goodsUnit);
        }
    }

    public void goodsUnitDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsUnitMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsUnit goodsUnit) {
        goodsUnit.setDelete(true);
        goodsUnitMapper.updateById(goodsUnit);
    }

    public void setDeleteFlagFalse(GoodsUnit goodsUnit) {
        goodsUnit.setDelete(false);
        goodsUnitMapper.updateById(goodsUnit);
    }

    public void goodsUnitAdd(GoodsUnit goodsUnit) {
        goodsUnit.setCreateTime(new Date());
        goodsUnit.setCreater("11");
        goodsUnitMapper.insert(goodsUnit);
    }
}
