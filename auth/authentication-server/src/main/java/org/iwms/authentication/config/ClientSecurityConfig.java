package org.iwms.authentication.config;

import org.iwms.authentication.controller.ResourceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author leung
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ClientSecurityConfig {
    static final Logger logger = LoggerFactory.getLogger(ClientSecurityConfig.class);
    /**
     * 安全配置
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("securityFilterChain");
        http.authorizeHttpRequests(authorize ->
                        // 任何请求都需要认证
                        authorize.anyRequest().authenticated()
                )
                // 登录
//                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/messaging-client-oidc"))
                .oauth2Login(Customizer.withDefaults())
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }

}
