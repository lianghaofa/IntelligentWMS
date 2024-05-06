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
@TableName("`transportationfee`")
public class TransportationFee {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("send_city")
    private String sendCity;

    @JsonProperty("receiver_city")
    private String receiverCity;

    @JsonProperty("weight_fee")
    private Double weightFee;

    @JsonProperty("volume_fee")
    private Double volumeFee;

    @JsonProperty("min_payment")
    private Double minPayment;

    @JsonProperty("transportation_supplier")
    private String transportationSupplier;

    private String creater;

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

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public Double getWeightFee() {
        return weightFee;
    }

    public void setWeightFee(Double weightFee) {
        this.weightFee = weightFee;
    }

    public Double getVolumeFee() {
        return volumeFee;
    }

    public void setVolumeFee(Double volumeFee) {
        this.volumeFee = volumeFee;
    }

    public Double getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(Double minPayment) {
        this.minPayment = minPayment;
    }

    public String getTransportationSupplier() {
        return transportationSupplier;
    }

    public void setTransportationSupplier(String transportationSupplier) {
        this.transportationSupplier = transportationSupplier;
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
