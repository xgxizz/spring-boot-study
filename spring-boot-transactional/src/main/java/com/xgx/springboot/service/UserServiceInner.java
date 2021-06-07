package com.xgx.springboot.service;

import com.xgx.springboot.mapper.UserMapper;
import com.xgx.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/4/9 12:47 <br/>
 */
@Service
public class UserServiceInner {
    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.MANDATORY)
    public void insertOne(){
        userMapper.addUser(new User("张北京", "北京"));
        System.out.println("Inner 完成addUser动作..");
        System.out.println("准备发生异常");
        System.out.println(1/0);
    }
}
