package org.iwms.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author leung
 */
@Data
@TableName("`goods`")
public class Goods {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("goods_code")
    private String goodsCode;

    @JsonProperty("goods_desc")
    private String goodsDesc;

    @JsonProperty("goods_supplier")
    private String goodsSupplier;

    @JsonProperty("goods_weight")
    private Double goodsWeight;

    @JsonProperty("goods_w")
    private Double goodsW;

    @JsonProperty("goods_d")
    private Double goodsD;

    @JsonProperty("goods_h")
    private Double goodsH;

    @JsonProperty("unit_volume")
    private Double unitVolume;

    @JsonProperty("goods_unit")
    private String goodsUnit;

    @JsonProperty("goods_class")
    private String goodsClass;

    @JsonProperty("goods_brand")
    private String goodsBrand;

    @JsonProperty("goods_color")
    private String goodsColor;

    @JsonProperty("goods_shape")
    private String goodsShape;

    @JsonProperty("goods_specs")
    private String goodsSpecs;

    @JsonProperty("goods_origin")
    private String goodsOrigin;

    @JsonProperty("safety_stock")
    private Long safetyStock;

    @JsonProperty("goods_cost")
    private Double goodsCost;

    @JsonProperty("goods_price")
    private Double goodsPrice;

    private String creater;

    @JsonProperty("bar_code")
    private String barCode;

    private String openid;

    @TableField("is_delete")
    private boolean isDelete;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGoodsSupplier() {
        return goodsSupplier;
    }

    public void setGoodsSupplier(String goodsSupplier) {
        this.goodsSupplier = goodsSupplier;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Double getGoodsW() {
        return goodsW;
    }

    public void setGoodsW(Double goodsW) {
        this.goodsW = goodsW;
    }

    public Double getGoodsD() {
        return goodsD;
    }

    public void setGoodsD(Double goodsD) {
        this.goodsD = goodsD;
    }

    public Double getGoodsH() {
        return goodsH;
    }

    public void setGoodsH(Double goodsH) {
        this.goodsH = goodsH;
    }

    public Double getUnitVolume() {
        return unitVolume;
    }

    public void setUnitVolume(Double unitVolume) {
        this.unitVolume = unitVolume;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsShape() {
        return goodsShape;
    }

    public void setGoodsShape(String goodsShape) {
        this.goodsShape = goodsShape;
    }

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public Long getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(Long safetyStock) {
        this.safetyStock = safetyStock;
    }

    public Double getGoodsCost() {
        return goodsCost;
    }

    public void setGoodsCost(Double goodsCost) {
        this.goodsCost = goodsCost;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
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
