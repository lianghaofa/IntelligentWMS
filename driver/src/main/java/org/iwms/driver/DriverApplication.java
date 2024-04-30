package org.iwms.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.driver.mapper")
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class);
    }

}
