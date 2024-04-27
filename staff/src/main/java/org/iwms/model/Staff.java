package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class Staff {

    // 员工姓名
    private String staffName;
    // 员工类型
    private String staffType;
    // 检查码，默认为8888
    private int checkCode;
    // 用户ID
    private String openid;
    // 是否删除标签，表示该员工是否已被删除
    private boolean isDelete;
    // 创建时间，记录了员工的创建时间
    private Date createTime;
    // 更新时间，记录了员工的最后更新时间
    private Date updateTime;
    // 检查码错误计数器
    private int errorCheckCodeCounter;
    // 是否锁定，表示员工是否被锁定
    private boolean isLock;

}
