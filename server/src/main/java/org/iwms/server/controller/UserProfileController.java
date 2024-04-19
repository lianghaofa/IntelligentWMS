package org.iwms.server.controller;

import org.iwms.server.model.UserProfile;
import org.iwms.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/getAllUsers")
    public List<UserProfile> getAllUsers() {
        return userProfileService.getAllUsers();
    }

    @GetMapping("/insertUserByName")
    public void insertUserByName(Integer userId,String name) {
        userProfileService.insertUserByUserIdAndName(userId, name);
    }


}