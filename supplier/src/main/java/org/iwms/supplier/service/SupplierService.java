package org.iwms.supplier.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.supplier.mapper.SupplierMapper;
import org.iwms.supplier.model.Supplier;
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
public class SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;


    public IPage<Supplier> getSupplierByPage(int pageNum, String name, int pageSize) {
        Page<Supplier> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "supplier_name", "supplier_city", "supplier_address", "supplier_contact", "supplier_level", "creater", "supplier_manager", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("supplier_name", name);
        }
        return supplierMapper.selectPage(page, queryWrapper);
    }

    public void supplierEdit(Supplier supplier) {
        // 如果id不为空，则进行更新操作
        if (supplier.getId() != null) {
            supplierMapper.updateById(supplier);
        } else {
            // 如果id为空，则进行插入操作
            supplierMapper.insert(supplier);
        }
    }

    public void supplierAdd(Supplier supplier) {
        supplier.setCreateTime(new Date());
        supplier.setCreater("11");
        supplierMapper.insert(supplier);
    }

    public Supplier supplierSelectById(Long id) {
        return supplierMapper.selectById(id);
    }

    public void setDeleteFlagTrue(Supplier supplier) {
        supplier.setDelete(true);
        supplierMapper.updateById(supplier);
    }

    public void setDeleteFlagFalse(Supplier supplier) {
        supplier.setDelete(false);
        supplierMapper.updateById(supplier);
    }

    public List<Supplier> getSuppliers(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return supplierMapper.selectByMap(whereMap);
    }
}
