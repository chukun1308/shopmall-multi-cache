package com.chukun.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.chukun.cache.mapper"})
public class ShopmallCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopmallCacheApplication.class, args);
    }

}
