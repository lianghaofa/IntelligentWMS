package org.iwms.model;

import java.util.Date;
/**
 * @author leung
 */
public class GoodsShape {

    // 商品形状
    private String goodsShape;
    // 创建者
    private String creater;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该商品形状是否已被删除
    private boolean isDelete;
    // 创建时间，记录了商品形状的创建时间
    private Date createTime;
    // 更新时间，记录了商品形状的最后更新时间
    private Date updateTime;

}
