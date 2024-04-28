package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class Customer {

    // 客户名称
    private String customerName;
    // 客户所在城市
    private String customerCity;
    // 客户地址
    private String customerAddress;
    // 客户联系方式
    private String customerContact;
    // 客户经理
    private String customerManager;
    // 客户级别
    private long customerLevel;
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

}
