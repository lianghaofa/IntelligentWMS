package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class Goods {

    // 商品编码
    private String goodsCode;
    // 商品描述
    private String goodsDesc;
    // 商品供应商
    private String goodsSupplier;
    // 商品重量
    private double goodsWeight;
    // 商品宽度
    private double goodsW;
    // 商品深度
    private double goodsD;
    // 商品高度
    private double goodsH;
    // 单位体积
    private double unitVolume;
    // 商品单位
    private String goodsUnit;
    // 商品类别
    private String goodsClass;
    // 商品品牌
    private String goodsBrand;
    // 商品颜色
    private String goodsColor;
    // 商品形状
    private String goodsShape;
    // 商品规格
    private String goodsSpecs;
    // 商品产地
    private String goodsOrigin;
    // 安全库存
    private long safetyStock;
    // 商品成本
    private double goodsCost;
    // 商品价格
    private double goodsPrice;
    // 创建者
    private String creator;
    // 条形码
    private String barCode;
    // 用户ID
    private String openid;
    // 是否删除标签
    private boolean isDelete;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
}
