package org.iwms.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.iwms.server")
public class MyBatisConfig {
}
