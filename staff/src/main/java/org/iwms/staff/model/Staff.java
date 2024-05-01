package org.iwms.staff.model;


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
@TableName("`staff`")
public class Staff {

    @TableId(type = IdType.AUTO)
    private Long id;
    // 员工姓名
    @JsonProperty("staff_name")
    private String staffName;
    // 员工类型
    @JsonProperty("staff_type")
    private String staffType;
    // 检查码，默认为8888
    @JsonProperty("check_code")
    private int checkCode;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该员工是否已被删除

    @TableField("is_delete")
    private boolean deletes;

    // 创建时间，记录了员工的创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 更新时间，记录了员工的最后更新时间
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 检查码错误计数器
    @JsonProperty("error_check_code_counter")
    private int errorCheckCodeCounter;
    // 是否锁定，表示员工是否被锁定

    // @JsonProperty("is_lock")
    @TableField("is_lock")
    private boolean locks;

    @JsonProperty("is_lock")
    @TableField("inactive")
    private boolean inactive;

    public boolean getInactive() {
        return inactive;
    }



    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    // 创建者
    private String creater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public int getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(int checkCode) {
        this.checkCode = checkCode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public boolean isDeletes() {
        return deletes;
    }

    public boolean getDeletes() {
        return deletes;
    }

    public void setDeletes(boolean deletes) {
        this.deletes = deletes;
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

    public int getErrorCheckCodeCounter() {
        return errorCheckCodeCounter;
    }

    public void setErrorCheckCodeCounter(int errorCheckCodeCounter) {
        this.errorCheckCodeCounter = errorCheckCodeCounter;
    }

    public boolean isLocks() {
        return locks;
    }


    public boolean getLocks() {
        return locks;
    }

    public void setLocks(boolean locks) {
        this.locks = locks;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
