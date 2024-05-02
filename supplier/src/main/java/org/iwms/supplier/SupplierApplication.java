package org.iwms.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.supplier.mapper")
public class SupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class);
    }

}
