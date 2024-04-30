package org.iwms.server.controller;

import org.iwms.server.model.UserProfile;
import org.iwms.server.service.MessageReceiver;
import org.iwms.server.service.MessageSender;
import org.iwms.server.service.RedisService;
import org.iwms.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leung
 */
@RestController
@RequestMapping("/auth")
public class TestController {


    @GetMapping("/hello")
    public String sayHello(){
        return "hello security";
    }

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send")
    public String sendValue(@RequestParam String key) {
        messageSender.sendMessage("my-queue", key);
        return "OK";
    }

    @GetMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "OK";
    }

    @GetMapping("/get")
    public Object getValue(@RequestParam String key) {
        return redisService.getValue(key);
    }


}