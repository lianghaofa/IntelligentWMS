package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class StockBin {
    // 库位名称
    private String binName;
    // 商品编号
    private String goodsCode;
    // 商品描述
    private String goodsDesc;
    // 库位库存数量
    private long goodsQty;
    // 库位拣货数量
    private long pickQty;
    // 库位已拣货数量
    private long pickedQty;
    // 库位尺寸
    private String binSize;
    // 库位属性
    private String binProperty;
    // 事务码
    private String tCode;
    // 用户ID
    private String openid;
    // 创建时间，记录了库位信息的创建时间
    private Date createTime;
    // 更新时间，记录了库位信息的最后更新时间
    private Date updateTime;

}
