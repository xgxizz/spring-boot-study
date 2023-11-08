package com.xgx.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: B
 * @Author: xu.gx
 * @Date: 2023/10/19 15:43
 **/
@Configuration
public class ObjectB {
    @Autowired
    private ObjectA a;
}
