package org.iwms.driver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
/**
 * @author leung
 */
@Data
@TableName("`dispatchlist`")
public class DispatchDetails {

    @TableId(type = IdType.AUTO)
    private Long id;

    // 驾驶员姓名
    @JsonProperty("driver_name")
    private String driverName;
    // DN编号
    @JsonProperty("dn_code")
    private String dnCode;
    // 联系方式
    private long contact;
    // 创建者
    private String creater;
    // 用户ID
    private String openid;
    // 创建时间
    @JsonProperty("create_time")
    private Date createTime;
    // 更新时间
    @JsonProperty("update_time")
    private Date updateTime;

    // 是否已删除
    @TableField(value = "is_delete") // 指定数据库表中对应的字段名
    private Boolean delete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDnCode() {
        return dnCode;
    }

    public void setDnCode(String dnCode) {
        this.dnCode = dnCode;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }





}
