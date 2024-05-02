
package org.iwms.capital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.capital.mapper.CapitalMapper;
import org.iwms.capital.model.Capital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iwms.common.core.utils.StringUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leung
 */
@Service
public class CapitalService {

    @Autowired
    private CapitalMapper capitalMapper;

    public IPage<Capital> getCapitalsByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<Capital> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Capital> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "capital_name", "capital_qty", "capital_cost", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("capital_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return capitalMapper.selectPage(page, queryWrapper);
    }

    public List<Capital> getCapitals(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return capitalMapper.selectByMap(whereMap);
    }


    public Capital capitalSelectById(Long id) {
        return capitalMapper.selectById(id);
    }

    public void capitalEdit(Capital capital) {
        // 如果id不为空，则进行更新操作
        if (capital.getId() != null) {
            capitalMapper.updateById(capital);
        } else {
            // 如果id为空，则进行插入操作
            capitalMapper.insert(capital);
        }
    }

    public void capitalDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            capitalMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(Capital capital) {
        capital.setDelete(true);
        capitalMapper.updateById(capital);
    }

    public void setDeleteFlagFalse(Capital capital) {
        capital.setDelete(false);
        capitalMapper.updateById(capital);
    }

    public void capitalAdd(Capital capital) {
        capital.setCreateTime(new Date());
        capital.setCreater("11");
        capitalMapper.insert(capital);
    }
}
