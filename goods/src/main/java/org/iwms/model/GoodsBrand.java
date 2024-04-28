package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class GoodsBrand {

    // 商品品牌
    private String goodsBrand;
    // 创建者
    private String creator;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该商品品牌是否已被删除
    private boolean isDelete;
    // 创建时间，记录了商品品牌的创建时间
    private Date createTime;
    // 更新时间，记录了商品品牌的最后更新时间
    private Date updateTime;

}
