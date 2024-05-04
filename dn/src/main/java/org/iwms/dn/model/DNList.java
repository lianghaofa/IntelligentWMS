package org.iwms.dn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`dnlist`")
public class DNList {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("dn_code")
    private String dnCode;

    @TableField("dn_status")
    private Long dnStatus;

    @TableField("total_weight")
    private Double totalWeight;

    @TableField("total_volume")
    private Double totalVolume;

    @TableField("total_cost")
    private Double totalCost;

    private String customer;

    private String creater;

    @TableField("bar_code")
    private String barCode;

    @TableField("back_order_label")
    private Boolean backOrderLabel;

    private String openid;

    @TableField("transportation_fee")
    private String transportationFee;

    @TableField("is_delete")
    private Boolean isDelete;

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

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getTransportationFee() {
        return transportationFee;
    }

    public void setTransportationFee(String transportationFee) {
        this.transportationFee = transportationFee;
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
