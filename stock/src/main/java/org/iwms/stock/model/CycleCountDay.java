package org.iwms.stock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`cyclecountday`")
public class CycleCountDay {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String openid;

    @TableField("cyclecount_status")
    private Integer cyclecountStatus;

    @TableField("bin_name")
    private String binName;

    @TableField("goods_code")
    private String goodsCode;

    @TableField("goods_qty")
    private Long goodsQty;

    @TableField("physical_inventory")
    private Long physicalInventory;

    private Long difference;

    private String creater;

    @TableField("t_code")
    private String tCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
}
