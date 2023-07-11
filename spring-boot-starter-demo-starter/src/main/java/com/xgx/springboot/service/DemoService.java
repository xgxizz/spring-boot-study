package com.xgx.springboot.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: <br/>
 *
 * @Author: xgx <br/>
 * date: 2021/1/15 16:10 <br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class DemoService {
    private String url;
    private String driver;
    private String username;
    private String password;


    public void connection(){
        log.info("连接Demo成功, {}", this);
    }
}
