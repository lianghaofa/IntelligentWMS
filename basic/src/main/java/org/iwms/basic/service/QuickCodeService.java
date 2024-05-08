package org.iwms.basic.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.iwms.basic.mapper.QuickCodeMapper;
import org.iwms.basic.model.QuickCode;
import org.iwms.common.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leung
 */
@Service
public class QuickCodeService {

    static final Logger logger = LoggerFactory.getLogger(QuickCodeService.class);

    @Autowired
    private QuickCodeMapper quickCodeMapper;


    @Transactional
    public IPage<QuickCode> getQuickCodesByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<QuickCode> page = new Page<>(pageNum, pageSize);

        QueryWrapper<QuickCode> queryWrapper = new QueryWrapper<>();
        // 指定要返回的字段
        queryWrapper.select("id", "driver_name", "license_plate", "contact", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return quickCodeMapper.selectPage(page, queryWrapper);
    }

    public List<QuickCode> getQuickCodes(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return quickCodeMapper.selectByMap(whereMap);
    }


    public QuickCode quickCodeSelectById(Long id) {
        return quickCodeMapper.selectById(id);
    }

    public void quickCodeEdit(QuickCode quickCode) {
        // 如果id不为空，则进行更新操作
        if (quickCode.getId() != null) {
            quickCodeMapper.updateById(quickCode);
        } else {
            // 如果id为空，则进行插入操作
            quickCodeMapper.insert(quickCode);
        }
    }

    public void quickCodeDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            quickCodeMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(QuickCode quickCode) {
        quickCode.setDelete(true);
        quickCodeMapper.updateById(quickCode);
    }

    public void setDeleteFlagFalse(QuickCode quickCode) {
        quickCode.setDelete(false);
        quickCodeMapper.updateById(quickCode);
    }

    public void quickCodeAdd(QuickCode quickCode) {
        quickCode.setCreateTime(new Date());
        quickCode.setCreater("11");
        quickCodeMapper.insert(quickCode);
    }

}
