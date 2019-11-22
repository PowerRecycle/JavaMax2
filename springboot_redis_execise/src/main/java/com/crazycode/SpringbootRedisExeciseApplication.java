package com.crazycode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.crazycode.mapper"})
public class SpringbootRedisExeciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisExeciseApplication.class, args);
    }

}
