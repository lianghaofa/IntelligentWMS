package org.iwms.stock.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("`stocklist`")
public class Stock {
    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("goods_code")
    private String goodsCode;
    @JsonProperty("goods_desc")
    private String goodsDesc;
    @JsonProperty("goods_qty")
    private Long goodsQty;
    @JsonProperty("onhand_stock")
    private Long onhandStock;
    @JsonProperty("can_order_stock")
    private Long canOrderStock;
    @JsonProperty("ordered_stock")
    private Long orderedStock;
    @JsonProperty("inspect_stock")
    private Long inspectStock;
    @JsonProperty("hold_stock")
    private Long holdStock;
    @JsonProperty("damage_stock")
    private Long damageStock;
    @JsonProperty("asn_stock")
    private Long asnStock;
    @JsonProperty("dn_stock")
    private Long dnStock;
    @JsonProperty("pre_load_stock")
    private Long preLoadStock;
    @JsonProperty("pre_sort_stock")
    private Long preSortStock;
    @JsonProperty("sorted_stock")
    private Long sortedStock;
    @JsonProperty("pick_stock")
    private Long pickStock;
    @JsonProperty("picked_stock")
    private Long pickedStock;
    @JsonProperty("back_order_stock")
    private Long backOrderStock;

    private String supplier;

    private String openid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public Long getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(Long goodsQty) {
        this.goodsQty = goodsQty;
    }

    public Long getOnhandStock() {
        return onhandStock;
    }

    public void setOnhandStock(Long onhandStock) {
        this.onhandStock = onhandStock;
    }

    public Long getCanOrderStock() {
        return canOrderStock;
    }

    public void setCanOrderStock(Long canOrderStock) {
        this.canOrderStock = canOrderStock;
    }

    public Long getOrderedStock() {
        return orderedStock;
    }

    public void setOrderedStock(Long orderedStock) {
        this.orderedStock = orderedStock;
    }

    public Long getInspectStock() {
        return inspectStock;
    }

    public void setInspectStock(Long inspectStock) {
        this.inspectStock = inspectStock;
    }

    public Long getHoldStock() {
        return holdStock;
    }

    public void setHoldStock(Long holdStock) {
        this.holdStock = holdStock;
    }

    public Long getDamageStock() {
        return damageStock;
    }

    public void setDamageStock(Long damageStock) {
        this.damageStock = damageStock;
    }

    public Long getAsnStock() {
        return asnStock;
    }

    public void setAsnStock(Long asnStock) {
        this.asnStock = asnStock;
    }

    public Long getDnStock() {
        return dnStock;
    }

    public void setDnStock(Long dnStock) {
        this.dnStock = dnStock;
    }

    public Long getPreLoadStock() {
        return preLoadStock;
    }

    public void setPreLoadStock(Long preLoadStock) {
        this.preLoadStock = preLoadStock;
    }

    public Long getPreSortStock() {
        return preSortStock;
    }

    public void setPreSortStock(Long preSortStock) {
        this.preSortStock = preSortStock;
    }

    public Long getSortedStock() {
        return sortedStock;
    }

    public void setSortedStock(Long sortedStock) {
        this.sortedStock = sortedStock;
    }

    public Long getPickStock() {
        return pickStock;
    }

    public void setPickStock(Long pickStock) {
        this.pickStock = pickStock;
    }

    public Long getPickedStock() {
        return pickedStock;
    }

    public void setPickedStock(Long pickedStock) {
        this.pickedStock = pickedStock;
    }

    public Long getBackOrderStock() {
        return backOrderStock;
    }

    public void setBackOrderStock(Long backOrderStock) {
        this.backOrderStock = backOrderStock;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
