package org.iwms.dn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.dn.mapper.DNDetailMapper;
import org.iwms.dn.mapper.DNListMapper;
import org.iwms.dn.model.DNList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author leung
 */
@Service
public class DNService {

    @Autowired
    private DNDetailMapper dnDetailMapper;

    @Autowired
    private DNListMapper dnListMapper;

    public IPage<DNList> getDNsByPage(int pageNum, String name, int pageSize) {
        Page<DNList> page = new Page<>(pageNum, pageSize);

        QueryWrapper<DNList> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "asn_code", "supplier", "total_volume", "total_cost", "transportation_fee",
                "create_time", "update_time", "creater");
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        return dnListMapper.selectPage(page, queryWrapper);
    }

    public void createByDetails(String asnCode, String creater, List<String> goodsCodes, List<Double> goodsQtys, String supplier) {


        // 只生成一张到货通知书

        // 一种货物一条明细
//        Assert.isTrue(goodsQtys.size() == goodsQtys.size());
//
//        for (int i = 0; i < goodsCodes.size(); i ++){
//            String goodsCode = goodsCodes.get(i);
//            Double goodsQty = goodsQtys.get(i);
//            ASNDetail asnDetail = new ASNDetail();
//            asnDetail.setAsnCode(asnCode);
//            asnDetail.setAsnStatus(0L);
//            asnDetail.setCreater(creater);
//            asnDetail.setGoodsQty(goodsQty);
//            asnDetail.setGoodsCode(goodsCode);
//            asnDetailMapper.insert(asnDetail);
//        }
//
//        ASNList asnList = new ASNList();
//        asnList.setAsnCode(asnCode);
//        asnList.setCreater(creater);
//        asnList.setSupplier(supplier);
//        asnList.setAsnStatus(0L);
//
//        asnListMapper.insert(asnList);
//



    }
}
