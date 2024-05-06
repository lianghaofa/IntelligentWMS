package org.iwms.asn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.asn.mapper")
@EnableDiscoveryClient
public class ASNApplication {

    public static void main(String[] args) {
        SpringApplication.run(ASNApplication.class);
    }

}
