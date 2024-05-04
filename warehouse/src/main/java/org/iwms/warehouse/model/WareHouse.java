package org.iwms.warehouse.model;

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
@TableName("`warehouse`")
public class WareHouse {

    @TableId(type = IdType.AUTO)
    private Long id;
    // 仓库名称
    @JsonProperty("warehouse_name")
    private String warehouseName;
    // 仓库所在城市
    @JsonProperty("warehouse_city")
    private String warehouseCity;
    // 仓库地址
    @JsonProperty("warehouse_address")
    private String warehouseAddress;
    // 仓库联系人
    @JsonProperty("warehouse_contact")
    private String warehouseContact;
    // 仓库管理员
    @JsonProperty("warehouse_manager")
    private String warehouseManager;
    // 创建者
    private String creater;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该仓库是否已被删除
    @TableField("is_delete")
    private boolean isDelete;
    // 创建时间，记录了仓库的创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 更新时间，记录了仓库的最后更新时间
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseCity() {
        return warehouseCity;
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseContact() {
        return warehouseContact;
    }

    public void setWarehouseContact(String warehouseContact) {
        this.warehouseContact = warehouseContact;
    }

    public String getWarehouseManager() {
        return warehouseManager;
    }

    public void setWarehouseManager(String warehouseManager) {
        this.warehouseManager = warehouseManager;
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
