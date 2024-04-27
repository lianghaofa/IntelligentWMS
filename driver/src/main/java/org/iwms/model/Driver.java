package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class Driver {

    // 驾驶员姓名
    private String driverName;
    // 车牌号
    private String licensePlate;
    // 联系方式
    private String contact;
    // 创建者
    private String creator;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该驾驶员信息是否已被删除
    private boolean isDelete;
    // 创建时间，记录了驾驶员信息的创建时间
    private Date createTime;
    // 更新时间，记录了驾驶员信息的最后更新时间
    private Date updateTime;

}
