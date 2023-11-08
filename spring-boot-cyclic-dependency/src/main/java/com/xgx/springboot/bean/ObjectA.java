package com.xgx.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Description: A
 * @Author: xu.gx
 * @Date: 2023/10/19 15:42
 **/
@Configuration
public class ObjectA {
    @Autowired
    // @Lazy
    private ObjectB b;
}
