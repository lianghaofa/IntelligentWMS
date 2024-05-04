package org.iwms.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.driver.mapper")
@EnableDiscoveryClient
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class);
    }

}
