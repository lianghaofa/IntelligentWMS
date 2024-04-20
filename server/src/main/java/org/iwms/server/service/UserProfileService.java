package org.iwms.server.service;

import com.google.common.base.Strings;
import org.iwms.server.mapper.UserProfileMapper;
import org.iwms.server.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leung
 */
@Service
public class UserProfileService {

    static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserProfileService(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    public List<UserProfile> getAllUsers() {
        return userProfileMapper.getAllUsers();
    }

    public void insertUserByName(String name) {
        // 校验相关逻辑
        if (!Strings.isNullOrEmpty(name)) {
            // name 有值且不为空字符串
            logger.info("name={}", name);
            userProfileMapper.insertUserByName(name);
        } else {
            // name 为空值或为空字符串
            logger.info("name 为空值或为空字符串");
        }
    }

    public void insertUserByUserIdAndName(Integer userId, String name) {
        // 校验相关逻辑
        if (userId != null && !Strings.isNullOrEmpty(name)) {
            // name 有值且不为空字符串
            logger.info("userId={},name={}", userId, name);
            userProfileMapper.insertUserByUserIdAndName(userId, name);
        } else {
            // name 为空值或为空字符串
            logger.info("userId={},name={}", userId, name);
        }
    }
}