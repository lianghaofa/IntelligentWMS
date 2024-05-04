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
@TableName("`binset`")
public class BinSet {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("bin_name")
    private String binName;

    @JsonProperty("bin_size")
    private String binSize;

    @JsonProperty("bin_property")
    private String binProperty;

    @JsonProperty("empty_label")
    private boolean emptyLabel;



    private String creater;

    @JsonProperty("bar_code")
    private String barCode;

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

    public String getBinName() {
        return binName;
    }

    public void setBinName(String binName) {
        this.binName = binName;
    }

    public String getBinSize() {
        return binSize;
    }

    public void setBinSize(String binSize) {
        this.binSize = binSize;
    }

    public String getBinProperty() {
        return binProperty;
    }

    public void setBinProperty(String binProperty) {
        this.binProperty = binProperty;
    }

    public boolean isEmptyLabel() {
        return emptyLabel;
    }

    public boolean getIsDelete(){
        return isDelete;
    }

    public boolean getEmptyLabel() {
        return emptyLabel;
    }

    public void setEmptyLabel(boolean emptyLabel) {
        this.emptyLabel = emptyLabel;
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
