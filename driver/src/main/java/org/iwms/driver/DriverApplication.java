package org.iwms.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.driver.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.iwms.driver"})
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class);
    }

}
