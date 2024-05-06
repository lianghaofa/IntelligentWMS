package org.iwms.asn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`asndetail`")
public class ASNDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("asn_code")
    @TableField("asn_code")
    private String asnCode;

    @JsonProperty("asn_status")
    @TableField("asn_status")
    private Long asnStatus;

    private String supplier;

    @JsonProperty("goods_code")
    @TableField("goods_code")
    private String goodsCode;

    @JsonProperty("goods_desc")
    @TableField("goods_desc")
    private String goodsDesc;

    @JsonProperty("goods_qty")
    @TableField("goods_qty")
    private Double goodsQty;

    @JsonProperty("goods_actual_qty")
    @TableField("goods_actual_qty")
    private Long goodsActualQty;

    @JsonProperty("sorted_qty")
    @TableField("sorted_qty")
    private Long sortedQty;

    @JsonProperty("goods_shortage_qty")
    @TableField("goods_shortage_qty")
    private Long goodsShortageQty;

    @JsonProperty("goods_more_qty")
    @TableField("goods_more_qty")
    private Long goodsMoreQty;

    @JsonProperty("goods_damage_qty")
    @TableField("goods_damage_qty")
    private Long goodsDamageQty;

    @JsonProperty("goods_weight")
    @TableField("goods_weight")
    private Double goodsWeight;

    @JsonProperty("goods_volume")
    @TableField("goods_volume")
    private Double goodsVolume;

    @JsonProperty("goods_cost")
    @TableField("goods_cost")
    private Double goodsCost;

    private String creater;

    private String openid;

    @JsonProperty("is_delete")
    @TableField("is_delete")
    private Boolean isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("create_time")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("update_time")
    @TableField("update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public Long getAsnStatus() {
        return asnStatus;
    }

    public void setAsnStatus(Long asnStatus) {
        this.asnStatus = asnStatus;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Double getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(Double goodsQty) {
        this.goodsQty = goodsQty;
    }

    public Long getGoodsActualQty() {
        return goodsActualQty;
    }

    public void setGoodsActualQty(Long goodsActualQty) {
        this.goodsActualQty = goodsActualQty;
    }

    public Long getSortedQty() {
        return sortedQty;
    }

    public void setSortedQty(Long sortedQty) {
        this.sortedQty = sortedQty;
    }

    public Long getGoodsShortageQty() {
        return goodsShortageQty;
    }

    public void setGoodsShortageQty(Long goodsShortageQty) {
        this.goodsShortageQty = goodsShortageQty;
    }

    public Long getGoodsMoreQty() {
        return goodsMoreQty;
    }

    public void setGoodsMoreQty(Long goodsMoreQty) {
        this.goodsMoreQty = goodsMoreQty;
    }

    public Long getGoodsDamageQty() {
        return goodsDamageQty;
    }

    public void setGoodsDamageQty(Long goodsDamageQty) {
        this.goodsDamageQty = goodsDamageQty;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Double getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(Double goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Double getGoodsCost() {
        return goodsCost;
    }

    public void setGoodsCost(Double goodsCost) {
        this.goodsCost = goodsCost;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
