package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsOriginMapper;
import org.iwms.goods.model.GoodsOrigin;
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
public class GoodsOriginService {

    @Autowired
    private GoodsOriginMapper goodsOriginMapper;

    public IPage<GoodsOrigin> getGoodsOriginsByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsOrigin> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsOrigin> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_origin", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_origin", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsOriginMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsOrigin> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsOriginMapper.selectByMap(whereMap);
    }


    public GoodsOrigin goodsOriginSelectById(Long id) {
        return goodsOriginMapper.selectById(id);
    }

    public void goodsOriginEdit(GoodsOrigin goodsOrigin) {
        // 如果id不为空，则进行更新操作
        if (goodsOrigin.getId() != null) {
            goodsOriginMapper.updateById(goodsOrigin);
        } else {
            // 如果id为空，则进行插入操作
            goodsOriginMapper.insert(goodsOrigin);
        }
    }

    public void goodsOriginDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsOriginMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsOrigin goodsOrigin) {
        goodsOrigin.setDelete(true);
        goodsOriginMapper.updateById(goodsOrigin);
    }

    public void setDeleteFlagFalse(GoodsOrigin goodsOrigin) {
        goodsOrigin.setDelete(false);
        goodsOriginMapper.updateById(goodsOrigin);
    }

    public void goodsOriginAdd(GoodsOrigin goodsOrigin) {
        goodsOrigin.setCreateTime(new Date());
        goodsOrigin.setCreater("11");
        goodsOriginMapper.insert(goodsOrigin);
    }
}
