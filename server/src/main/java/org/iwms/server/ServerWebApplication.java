package org.iwms.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author leung
 */
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"org.iwms"})
public class ServerWebApplication {

    // static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerWebApplication.class, args);
    }
}
