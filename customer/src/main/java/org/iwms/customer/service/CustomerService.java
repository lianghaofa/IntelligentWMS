package org.iwms.customer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.customer.mapper.CustomerMapper;
import org.iwms.customer.model.Customer;
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
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    public IPage<Customer> getCustomerByPage(int pageNum, String name, int pageSize) {
        Page<Customer> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "customer_name", "customer_city", "customer_address", "customer_contact", "customer_level", "creater", "customer_manager", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("customer_name", name);
        }
        return customerMapper.selectPage(page, queryWrapper);
    }

    public void customerEdit(Customer customer) {
        // 如果id不为空，则进行更新操作
        if (customer.getId() != null) {
            customerMapper.updateById(customer);
        } else {
            // 如果id为空，则进行插入操作
            customerMapper.insert(customer);
        }
    }

    public void customerAdd(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setCreater("11");
        customerMapper.insert(customer);
    }

    public Customer customerSelectById(Long id) {
        return customerMapper.selectById(id);
    }

    public void setDeleteFlagTrue(Customer customer) {
        customer.setDelete(true);
        customerMapper.updateById(customer);
    }

    public void setDeleteFlagFalse(Customer customer) {
        customer.setDelete(false);
        customerMapper.updateById(customer);
    }

    public List<Customer> getCustomers(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return customerMapper.selectByMap(whereMap);
    }
}
