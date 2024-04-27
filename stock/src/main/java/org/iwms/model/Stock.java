package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class Stock {

    // 商品编号
    private String goodsCode;
    // 商品描述
    private String goodsDesc;
    // 总库存数量
    private long goodsQty;
    // 在手库存
    private long onHandStock;
    // 可订货库存
    private long canOrderStock;
    // 已订货库存
    private long orderedStock;
    // 检验库存
    private long inspectStock;
    // 冻结库存
    private long holdStock;
    // 损坏库存
    private long damageStock;
    // ASN 库存
    private long asnStock;
    // DN 库存
    private long dnStock;
    // 预装车库存
    private long preLoadStock;
    // 预分拣库存
    private long preSortStock;
    // 已分拣库存
    private long sortedStock;
    // 可拣货库存
    private long pickStock;
    // 已拣货库存
    private long pickedStock;
    // 延迟发货库存
    private long backOrderStock;
    // 供应商
    private String supplier;
    // 用户ID
    private String openid;
    // 创建时间，记录了库存信息的创建时间
    private Date createTime;
    // 更新时间，记录了库存信息的最后更新时间
    private Date updateTime;
}
