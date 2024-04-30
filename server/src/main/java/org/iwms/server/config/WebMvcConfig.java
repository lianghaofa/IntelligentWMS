package org.iwms.server.config;

import org.iwms.server.interceptor.RequestTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author leung
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 请求时间拦截器
        registry.addInterceptor(new RequestTimeInterceptor()).addPathPatterns("/**");
    }
}
