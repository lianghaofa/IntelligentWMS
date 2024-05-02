package org.iwms.capital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.capital.mapper")
public class CapitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapitalApplication.class);
    }

}
