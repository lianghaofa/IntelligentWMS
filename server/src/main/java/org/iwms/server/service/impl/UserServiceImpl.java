package org.iwms.server.service.impl;

import org.iwms.server.model.Role;
import org.iwms.server.model.User;
import org.iwms.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.iwms.server.constant.enumerate.RoleType;
import java.util.List;

/**
 * @author leung
 */
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByName(String userName) {
        if (!"shusheng007".equals(userName)) {
            throw new RuntimeException("用户不存在");
        }

//        List<Role> roles = List.of(new Role(RoleType.ADMIN), new Role(RoleType.USER));
        List<Role> roles = List.of( new Role(RoleType.USER));
        return new User(userName, passwordEncoder.encode("123456"), roles);
    }
}
