package org.iwms.server.service;

import org.iwms.server.model.User;

public interface UserService {
    User getUserByName(String userName);
}
