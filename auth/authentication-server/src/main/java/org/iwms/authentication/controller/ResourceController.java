package org.iwms.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author leung
 */
@Slf4j
@RestController
public class ResourceController {
    static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @GetMapping("/server/a/resource1")
    public String getServerARes1(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("getServerARes1");
        return getServer("http://127.0.0.1:8001/resource1", oAuth2AuthorizedClient);
    }

    @GetMapping("/server/a/resource2")
    public String getServerARes2(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("getServerARes2");
        String server = getServer("http://127.0.0.1:8001/resource2", oAuth2AuthorizedClient);
        logger.info("result=" + server);
        return server;
    }

    @GetMapping("/server/a/resource3")
    public String getServerBRes1(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("getServerBRes1");
        String server = getServer("http://127.0.0.1:8001/resource3", oAuth2AuthorizedClient);
        logger.info("result=" + server);
        return server;
    }

    @GetMapping("/server/a/publicResource")
    public String getServerBRes2(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("getServerBRes2");
        String server = getServer("http://127.0.0.1:8001/api/publicResource", oAuth2AuthorizedClient);
        logger.info("result=" + server);
        return server;

    }

    /**
     * 绑定token，请求微服务
     */
    private String getServer(String url, OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        logger.info("getServer");
        // 获取 access_token
        String tokenValue = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        // 发起请求
        Mono<String> stringMono = WebClient.builder()
                .defaultHeader("Authorization", "Bearer " + tokenValue)
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return stringMono.block();
    }
}
