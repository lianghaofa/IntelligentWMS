package org.iwms.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.goods.mapper.GoodsBrandMapper;
import org.iwms.goods.model.GoodsBrand;
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
public class GoodsBrandService {

    @Autowired
    private GoodsBrandMapper goodsBrandMapper;

    public IPage<GoodsBrand> getGoodsBrandsByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<GoodsBrand> page = new Page<>(pageNum, pageSize);

        QueryWrapper<GoodsBrand> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段

        queryWrapper.select("id", "goods_brand", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_brand", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return goodsBrandMapper.selectPage(page, queryWrapper);
    }

    public List<GoodsBrand> getUsers(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return goodsBrandMapper.selectByMap(whereMap);
    }


    public GoodsBrand goodsBrandSelectById(Long id) {
        return goodsBrandMapper.selectById(id);
    }

    public void goodsBrandEdit(GoodsBrand goodsBrand) {
        // 如果id不为空，则进行更新操作
        if (goodsBrand.getId() != null) {
            goodsBrandMapper.updateById(goodsBrand);
        } else {
            // 如果id为空，则进行插入操作
            goodsBrandMapper.insert(goodsBrand);
        }
    }

    public void goodsBrandDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            goodsBrandMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(GoodsBrand goodsBrand) {
        goodsBrand.setDelete(true);
        goodsBrandMapper.updateById(goodsBrand);
    }

    public void setDeleteFlagFalse(GoodsBrand goodsBrand) {
        goodsBrand.setDelete(false);
        goodsBrandMapper.updateById(goodsBrand);
    }

    public void goodsBrandAdd(GoodsBrand goodsBrand) {
        goodsBrand.setCreateTime(new Date());
        goodsBrand.setCreater("11");
        goodsBrandMapper.insert(goodsBrand);
    }
}
