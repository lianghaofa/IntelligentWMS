package org.iwms.gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class CORSFilter {

//    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";
//
//    private static final String ALLOWED_METHODS = "POST, GET, OPTIONS, DELETE";
//
//    private static final String ALLOWED_ORIGIN = "*";
//
//    private static final String MAX_AGE = "3600";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        ServerHttpResponse response = exchange.getResponse();
//
//        HttpHeaders headers = response.getHeaders();
//
//        headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
//
//        headers.add("Access-Control-Max-Age", MAX_AGE);
//
//        if (request.getMethod() == HttpMethod.OPTIONS) {
//            response.setStatusCode(HttpStatus.OK);
//            return Mono.empty();
//        }
//        return chain. filter (exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
}
