package org.iwms.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.supplier.mapper")
//开启feign接口扫描，指定扫描的包
@EnableFeignClients(basePackages = {"org.iwms.supplier"})
public class SupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class);
    }

}
