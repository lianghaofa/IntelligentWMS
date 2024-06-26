package org.iwms.driver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.Date;

/**
 * @author leung
 */
@Data
@TableName("`driver`")
@ApiModel(value = "司机", description = "查询司机")
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "司机id")
    private Long id;
    // 驾驶员姓名
    @JsonProperty("driver_name")
    @ApiModelProperty(value = "司机名")
    private String driverName;
    // 车牌号
    @JsonProperty("license_plate")
    private String licensePlate;
    // 联系方式
    private String contact;
    // 创建者
    private String creater;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该驾驶员信息是否已被删除
    @TableField("is_delete")
    private boolean delete;
    // 创建时间，记录了驾驶员信息的创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 更新时间，记录了驾驶员信息的最后更新时间
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
