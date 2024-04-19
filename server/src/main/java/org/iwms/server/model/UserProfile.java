package org.iwms.server.model;


import java.util.Date;

public class UserProfile {
    /**
     * 用户 ID
     */
    private int userId = 0;

    /**
     * 姓名
     */
    private String name;

    /**
     * VIP 等级
     */
    private long vip = 1;

    /**
     * OpenID
     */
    private String openid;

    /**
     * 应用 ID
     */
    private String appid;

    /**
     * 是否删除
     */
    private boolean isDelete = false;

    /**
     * 是否是开发者
     */
    private boolean developer = true;

    /**
     * 交易码
     */
    private String tCode;

    /**
     * 注册 IP 地址
     */
    private String ip;

    /**
     * VIP 到期时间
     */
    private Date vipTime = new Date();

    /**
     * 是否关联到其他对象
     */
    private boolean linkTo = false;

    /**
     * 关联到的对象 ID
     */
    private long linkToId = 0;

    /**
     * 头像地址
     */
    private String avatar = "/static/img/user.jpg";

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 更新时间
     */
    private Date updateTime = new Date();

    // 构造函数
    public UserProfile(String name, String openid, String appid, String tCode, String ip) {
        this.name = name;
        this.openid = openid;
        this.appid = appid;
        this.tCode = tCode;
        this.ip = ip;
    }
}
