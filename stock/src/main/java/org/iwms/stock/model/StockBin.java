package org.iwms.stock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author leung
 */
@Data
@TableName("`stockbin`")
public class StockBin {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("bin_name")
    private String binName;

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

    @TableField("bin_size")
    private String binSize;

    @TableField("bin_property")
    private String binProperty;

    @TableField("t_code")
    private String tCode;

    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBinName() {
        return binName;
    }

    public void setBinName(String binName) {
        this.binName = binName;
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

    public String getBinSize() {
        return binSize;
    }

    public void setBinSize(String binSize) {
        this.binSize = binSize;
    }

    public String getBinProperty() {
        return binProperty;
    }

    public void setBinProperty(String binProperty) {
        this.binProperty = binProperty;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
