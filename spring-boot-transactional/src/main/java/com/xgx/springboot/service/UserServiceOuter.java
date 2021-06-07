package com.xgx.springboot.service;

import com.xgx.springboot.mapper.UserMapper;
import com.xgx.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description: <br/>
 * 外层服务
 * @author: xgx <br/>
 * date: 2021/4/9 12:44 <br/>
 */
@Service
public class UserServiceOuter {
    @Resource
    private UserServiceInner innerService;
    @Resource
    private UserMapper userMapper;

    @Transactional
    public void invokeInner(){
        userMapper.addUser(new User("外部名", "外部地址"));
        System.out.println("Outer 完成addUser动作.");
        innerService.insertOne();
        System.out.println("Outer invoke inner finished");
//        System.out.println("Outer 准备发生异常");
//        System.out.println(1/0);
    }
}
