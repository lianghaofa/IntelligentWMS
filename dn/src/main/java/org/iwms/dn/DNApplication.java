package org.iwms.dn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.dn.mapper")
public class DNApplication {

    public static void main(String[] args) {
        SpringApplication.run(DNApplication.class);
    }

}
