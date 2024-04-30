package org.iwms.oauthresource.controller;

import org.iwms.oauthresource.handler.UnAccessDeniedHandler;
import org.iwms.oauthresource.utils.RespJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leung
 */
@RestController
public class MessagesController {

    static final Logger logger = LoggerFactory.getLogger(MessagesController.class);
    @GetMapping("/resource1")
    public RespJson<String> getResource1(){
        logger.info("getResource1");
        return RespJson.success("服务A -> 资源1 -> 读权限");
    }

    @GetMapping("/resource2")
    public RespJson<String> getResource2(){
        logger.info("getResource2");
        return RespJson.success("服务A -> 资源2 -> 写权限");
    }

    @GetMapping("/resource3")
    public RespJson<String> resource3(){
        logger.info("resource3");
        return RespJson.success("服务A -> 资源3 -> profile 权限");
    }

    @GetMapping("/api/publicResource")
    public RespJson<String> publicResource() {
        logger.info("publicResource");
        return RespJson.success("服务A -> 公共资源");
    }
}
