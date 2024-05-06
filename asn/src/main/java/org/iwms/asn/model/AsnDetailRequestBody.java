package org.iwms.asn.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author leung
 */
public class AsnDetailRequestBody {
    @JsonProperty("asn_code")
    private String asnCode;
    private String supplier;
    @JsonProperty("goods_code")
    private List<String> goodsCode;

    @JsonProperty("goods_qty")
    private List<Double> goodsQty;
    private String creater;


    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<String> getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(List<String> goodsCode) {
        this.goodsCode = goodsCode;
    }

    public List<Double> getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(List<Double> goodsQty) {
        this.goodsQty = goodsQty;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
