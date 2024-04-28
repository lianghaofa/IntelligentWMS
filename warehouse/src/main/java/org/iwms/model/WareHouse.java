package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class WareHouse {

    // 仓库名称
    private String warehouseName;
    // 仓库所在城市
    private String warehouseCity;
    // 仓库地址
    private String warehouseAddress;
    // 仓库联系人
    private String warehouseContact;
    // 仓库管理员
    private String warehouseManager;
    // 创建者
    private String creator;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该仓库是否已被删除
    private boolean isDelete;
    // 创建时间，记录了仓库的创建时间
    private Date createTime;
    // 更新时间，记录了仓库的最后更新时间
    private Date updateTime;

}
