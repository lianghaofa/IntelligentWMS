package org.iwms.supplier.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`supplier`")
public class Supplier {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("supplier_city")
    private String supplierCity;

    @JsonProperty("supplier_address")
    private String supplierAddress;

    @JsonProperty("supplier_contact")
    private String supplierContact;

    @JsonProperty("supplier_manager")
    private String supplierManager;

    @JsonProperty("supplier_level")
    private Long supplierLevel;

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

    public boolean getIsDelete(){
        return isDelete;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierManager() {
        return supplierManager;
    }

    public void setSupplierManager(String supplierManager) {
        this.supplierManager = supplierManager;
    }

    public Long getSupplierLevel() {
        return supplierLevel;
    }

    public void setSupplierLevel(Long supplierLevel) {
        this.supplierLevel = supplierLevel;
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
