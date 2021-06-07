package com.xgx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/4/9 10:51 <br/>
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xgx.springboot.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
