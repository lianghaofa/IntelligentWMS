package org.iwms.server.model;

import lombok.Data;

/**
 * @author leung
 */
@Data
public class SignInReq {
    private String username;
    private String password;
}
