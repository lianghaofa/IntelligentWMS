
package org.iwms.payment.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.payment.mapper.TransportationFeeMapper;
import org.iwms.payment.model.TransportationFee;
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
public class TransportationFeeService {

    @Autowired
    private TransportationFeeMapper transportationFeeMapper;

    public IPage<TransportationFee> getTransportationFeesByPage(int pageNum, String name, int pageSize) {
        // 创建分页对象
        Page<TransportationFee> page = new Page<>(pageNum, pageSize);

        QueryWrapper<TransportationFee> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id", "send_city", "receiver_city", "weight_fee",
                "volume_fee", "min_payment", "transportation_supplier", "create_time", "update_time", "creater");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("transportationFee_name", name);
        }
        // 调用 MyBatis-Plus 的分页查询方法
        return transportationFeeMapper.selectPage(page, queryWrapper);
    }

    public List<TransportationFee> getTransportationFees(Boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return transportationFeeMapper.selectByMap(whereMap);
    }


    public TransportationFee transportationFeeSelectById(Long id) {
        return transportationFeeMapper.selectById(id);
    }

    public void transportationFeeEdit(TransportationFee transportationFee) {
        // 如果id不为空，则进行更新操作
        if (transportationFee.getId() != null) {
            transportationFeeMapper.updateById(transportationFee);
        } else {
            // 如果id为空，则进行插入操作
            transportationFeeMapper.insert(transportationFee);
        }
    }

    public void transportationFeeDelete(Long id) {
        // 如果id不为空，则进行更新操作
        if (null != id) {
            transportationFeeMapper.deleteById(id);
        }
    }

    public void setDeleteFlagTrue(TransportationFee transportationFee) {
        transportationFee.setDelete(true);
        transportationFeeMapper.updateById(transportationFee);
    }

    public void setDeleteFlagFalse(TransportationFee transportationFee) {
        transportationFee.setDelete(false);
        transportationFeeMapper.updateById(transportationFee);
    }

    public void transportationFeeAdd(TransportationFee transportationFee) {
        transportationFee.setCreateTime(new Date());
        transportationFee.setCreater("11");
        transportationFeeMapper.insert(transportationFee);
    }
}
