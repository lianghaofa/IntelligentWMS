package org.iwms.dn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`dndetail`")
public class DNDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("dn_code")
    private String dnCode;

    @TableField("dn_status")
    private Long dnStatus;

    private String customer;

    @TableField("goods_code")
    private String goodsCode;

    @TableField("goods_desc")
    private String goodsDesc;

    @TableField("goods_qty")
    private Long goodsQty;

    @TableField("pick_qty")
    private Long pickQty;

    @TableField("picked_qty")
    private Long pickedQty;

    @TableField("intransit_qty")
    private Long intransitQty;

    @TableField("delivery_actual_qty")
    private Long deliveryActualQty;

    @TableField("delivery_shortage_qty")
    private Long deliveryShortageQty;

    @TableField("delivery_more_qty")
    private Long deliveryMoreQty;

    @TableField("delivery_damage_qty")
    private Long deliveryDamageQty;

    @TableField("goods_weight")
    private Double goodsWeight;

    @TableField("goods_volume")
    private Double goodsVolume;

    @TableField("goods_cost")
    private Double goodsCost;

    private String creater;

    @TableField("back_order_label")
    private Boolean backOrderLabel;

    private String openid;

    @TableField("is_delete")
    private Boolean isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    public Long getDeliveryShortageQty() {
        return deliveryShortageQty;
    }

    public void setDeliveryShortageQty(Long deliveryShortageQty) {
        this.deliveryShortageQty = deliveryShortageQty;
    }

    public Long getDeliveryMoreQty() {
        return deliveryMoreQty;
    }

    public void setDeliveryMoreQty(Long deliveryMoreQty) {
        this.deliveryMoreQty = deliveryMoreQty;
    }

    public Long getDeliveryDamageQty() {
        return deliveryDamageQty;
    }

    public void setDeliveryDamageQty(Long deliveryDamageQty) {
        this.deliveryDamageQty = deliveryDamageQty;
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

    public Boolean getBackOrderLabel() {
        return backOrderLabel;
    }

    public void setBackOrderLabel(Boolean backOrderLabel) {
        this.backOrderLabel = backOrderLabel;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDnCode() {
        return dnCode;
    }

    public void setDnCode(String dnCode) {
        this.dnCode = dnCode;
    }

    public Long getDnStatus() {
        return dnStatus;
    }

    public void setDnStatus(Long dnStatus) {
        this.dnStatus = dnStatus;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public Long getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(Long goodsQty) {
        this.goodsQty = goodsQty;
    }

    public Long getPickQty() {
        return pickQty;
    }

    public void setPickQty(Long pickQty) {
        this.pickQty = pickQty;
    }

    public Long getPickedQty() {
        return pickedQty;
    }

    public void setPickedQty(Long pickedQty) {
        this.pickedQty = pickedQty;
    }

    public Long getIntransitQty() {
        return intransitQty;
    }

    public void setIntransitQty(Long intransitQty) {
        this.intransitQty = intransitQty;
    }

    public Long getDeliveryActualQty() {
        return deliveryActualQty;
    }

    public void setDeliveryActualQty(Long deliveryActualQty) {
        this.deliveryActualQty = deliveryActualQty;
    }
}
