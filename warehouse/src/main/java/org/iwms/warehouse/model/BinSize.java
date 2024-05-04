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
@TableName("`binsize`")
public class BinSize {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonProperty("bin_size")
    private String binSize;

    @JsonProperty("bin_size_w")
    private Double binSizeW;

    @JsonProperty("bin_size_d")
    private Double binSizeD;

    @JsonProperty("bin_size_h")
    private Double binSizeH;

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

    public String getBinSize() {
        return binSize;
    }

    public void setBinSize(String binSize) {
        this.binSize = binSize;
    }

    public boolean getIsDelete(){
        return isDelete;
    }

    public Double getBinSizeW() {
        return binSizeW;
    }

    public void setBinSizeW(Double binSizeW) {
        this.binSizeW = binSizeW;
    }

    public Double getBinSizeD() {
        return binSizeD;
    }

    public void setBinSizeD(Double binSizeD) {
        this.binSizeD = binSizeD;
    }

    public Double getBinSizeH() {
        return binSizeH;
    }

    public void setBinSizeH(Double binSizeH) {
        this.binSizeH = binSizeH;
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
