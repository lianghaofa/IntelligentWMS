package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class AsnDetailModel {
    // ASN编号
    private String asnCode;
    // ASN状态
    private long asnStatus;
    // 供应商
    private String supplier;
    // 商品编码
    private String goodsCode;
    // 商品描述
    private String goodsDesc;
    // 商品数量
    private long goodsQty;
    // 实际商品数量
    private long goodsActualQty;
    // 分拣数量
    private long sortedQty;
    // 商品短缺数量
    private long goodsShortageQty;
    // 商品超量数量
    private long goodsMoreQty;
    // 商品损坏数量
    private long goodsDamageQty;
    // 商品重量
    private double goodsWeight;
    // 商品体积
    private double goodsVolume;
    // 商品成本
    private double goodsCost;
    // 创建者
    private String creator;
    // 用户ID
    private String openid;
    // 是否删除标签
    private boolean isDelete;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    // Getters and setters
}
