package org.iwms.supplier.model;

import java.util.Date;
import java.util.Map;

/**
 * @author leung
 */
public class AsnListModel {
    // ASN编号
    private String asnCode;
    // ASN状态
    private long asnStatus;
    // 总重量
    private double totalWeight;
    // 总体积
    private double totalVolume;
    // 总成本
    private double totalCost;
    // 供应商
    private String supplier;
    // 创建者
    private String creater;
    // 条形码
    private String barCode;
    // 用户ID
    private String openid;
    // 运输费用
    private Map<String, Object> transportationFee;
    // 是否删除标签
    private boolean isDelete;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    // Getters and setters
}
