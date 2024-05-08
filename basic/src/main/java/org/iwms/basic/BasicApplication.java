package org.iwms.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.basic.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.iwms.basic"})
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class);
    }

}
