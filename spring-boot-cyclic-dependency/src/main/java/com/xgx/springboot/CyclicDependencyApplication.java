package com.xgx.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xgx
 */
@SpringBootApplication
public class CyclicDependencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CyclicDependencyApplication.class, args);
    }
}