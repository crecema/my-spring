package com.crecema.my.spring.boot.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.crecema.my.spring.boot.data.mapper")
public class BeanConfig {
}
