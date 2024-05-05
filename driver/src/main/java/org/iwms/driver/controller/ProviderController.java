package org.iwms.driver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author leung
 */
@RestController
public class ProviderController {
    @RequestMapping("/provider/list")
    public List<String> list(){
        List<String> list = new ArrayList<>();
        list.add("java技术爱好者");
        list.add("SpringCloud");
        list.add("没有人比我更懂了");
        Random random = new Random();
        int sleepTime = random.nextInt(3000) + 1000;
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
