package org.iwms.asn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.asn.mapper.ASNDetailMapper;
import org.iwms.asn.mapper.ASNListMapper;
import org.iwms.asn.model.ASNDetail;
import org.iwms.asn.model.ASNList;
import org.iwms.common.core.utils.CodeGenerateUtil;
import org.iwms.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leung
 */
@Service
public class ASNService {

    @Autowired
    private ASNDetailMapper asnDetailMapper;

    @Autowired
    private ASNListMapper asnListMapper;

    public IPage<ASNList> getASNsByPage(int pageNum, String name, int pageSize) {
        Page<ASNList> page = new Page<>(pageNum, pageSize);

        QueryWrapper<ASNList> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "asn_code", "asn_status", "supplier", "total_weight", "bar_code", "total_volume", "total_cost", "transportation_fee",
                "create_time", "update_time", "creater");
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("goods_code", name);
        }
        return asnListMapper.selectPage(page, queryWrapper);
    }

    public void createByDetails(String asnCode, String creater, List<String> goodsCodes, List<Double> goodsQtys, String supplier) {


        // 只生成一张到货通知书

        // 一种货物一条明细
        Assert.isTrue(goodsQtys.size() == goodsQtys.size());
        asnCode = CodeGenerateUtil.generateCodeByDate("ASN");
        for (int i = 0; i < goodsCodes.size(); i ++){
            String goodsCode = goodsCodes.get(i);
            Double goodsQty = goodsQtys.get(i);
            ASNDetail asnDetail = new ASNDetail();
            asnDetail.setAsnCode(asnCode);
            asnDetail.setAsnStatus(0L);
            asnDetail.setCreater(creater);
            asnDetail.setGoodsQty(goodsQty);
            asnDetail.setGoodsCode(goodsCode);
            asnDetail.setAsnCode(asnCode);
            asnDetail.setSupplier(supplier);
            asnDetail.setCreateTime(new Date());
            asnDetailMapper.insert(asnDetail);
        }

        ASNList asnList = new ASNList();
        asnList.setAsnCode(asnCode);
        asnList.setCreater(creater);
        asnList.setSupplier(supplier);
        asnList.setAsnStatus(0L);
        asnList.setCreateTime(new Date());
        asnListMapper.insert(asnList);

    }
}
