package com.xgx.springboot.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/1/15 16:10 <br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DemoService {
    private String url;
    private String driver;
    private String username;
    private String password;


    public void connection(){
        System.out.println("连接Demo成功," + this.toString());
    }
}
