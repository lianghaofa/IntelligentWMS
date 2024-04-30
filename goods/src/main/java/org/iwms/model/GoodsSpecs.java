package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class GoodsSpecs {

    // 商品规格
    private String goodsSpecs;
    // 创建者
    private String creater;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该商品规格是否已被删除
    private boolean isDelete;
    // 创建时间，记录了商品规格的创建时间
    private Date createTime;
    // 更新时间，记录了商品规格的最后更新时间
    private Date updateTime;
}
