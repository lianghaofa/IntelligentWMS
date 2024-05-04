package org.iwms.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.warehouse.mapper")
public class WareHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareHouseApplication.class);
    }

}
