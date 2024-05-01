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
@TableName("`stafftype`")
public class StaffType {

    @TableId(type = IdType.AUTO)
    private Long id;
    // 员工类型
    @JsonProperty("staff_type")
    private String staffType;
    // 用户ID
    private String openid;
    // 创建者
    private String creater;
    // 创建时间，记录了员工类型的创建时间
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 更新时间，记录了员工类型的最后更新时间
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField("is_delete")
    private boolean deletes;

}
