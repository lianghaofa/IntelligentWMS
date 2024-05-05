package org.iwms.supplier.controller;

import org.iwms.supplier.feign.ProviderClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author leung
 */
@RestController
public class ConsumerController {
    //引入Feign客户端
    @Resource
    private ProviderClient providerClient;

    @RequestMapping("/consumer/callProvider")
    public String callProvider() {
        //使用Feign客户端调用其他服务的接口
        return providerClient.list();
    }
}
