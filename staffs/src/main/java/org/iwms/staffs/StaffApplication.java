package org.iwms.staffs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leung
 */
@SpringBootApplication
@MapperScan("org.iwms.staffs.mapper")
public class StaffApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffApplication.class);
    }

}
