package org.iwms.user.model;

import lombok.Data;

/**
 * @author leung
 */
@Data
public class Permission {

    private String id;
    private String code;         //用户权限
    private String description;  //权限描述
    private String url;          //资源url

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
