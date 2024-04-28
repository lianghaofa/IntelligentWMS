package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class GoodsClass {

    // 商品类别
    private String goodsClass;
    // 创建者
    private String creator;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该商品类别是否已被删除
    private boolean isDelete;
    // 创建时间，记录了商品类别的创建时间
    private Date createTime;
    // 更新时间，记录了商品类别的最后更新时间
    private Date updateTime;
}
