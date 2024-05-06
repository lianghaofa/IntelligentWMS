package org.iwms.asn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("`asnlist`")
public class ASNList {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("asn_code")
    @JsonProperty("asn_code")
    private String asnCode;


    @TableField("asn_status")
    @JsonProperty("asn_status")
    private Long asnStatus;

    @TableField("total_weight")
    @JsonProperty("total_weight")
    private Double totalWeight;

    @TableField("total_volume")
    @JsonProperty("total_volume")
    private Double totalVolume;

    @TableField("total_cost")
    @JsonProperty("total_cost")
    private Double totalCost;

    private String supplier;

    private String creater;

    @TableField("bar_code")
    @JsonProperty("bar_code")
    private String barCode;

    private String openid;

    @TableField("transportation_fee")
    @JsonProperty("transportation_fee")
    private List<TransportationFee> transportationFee;

    @TableField("is_delete")
    @JsonProperty("is_delete")
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public List<TransportationFee> getTransportationFee() {
        return transportationFee;
    }

    public void setTransportationFee(List<TransportationFee> transportationFee) {
        this.transportationFee = transportationFee;
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
