package org.iwms.model;

import java.util.Date;

/**
 * @author leung
 */
public class StaffType {

    // 员工类型
    private String staffType;
    // 用户ID
    private String openid;
    // 创建者
    private String creator;
    // 创建时间，记录了员工类型的创建时间
    private Date createTime;
    // 更新时间，记录了员工类型的最后更新时间
    private Date updateTime;
}
