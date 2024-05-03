package org.iwms.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author leung
 */
@Component
public class AuthorizeFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 在这里编写您的拦截逻辑，对请求进行处理

        // 示例：输出请求路径
        System.out.println("Request Path: " + exchange.getRequest().getPath());
        // 调用链中的下一个过滤器
        return chain.filter(exchange);
    }
}
