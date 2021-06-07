package com.xgx.springboot;

import com.xgx.springboot.mapper.UserMapper;
import com.xgx.springboot.model.User;
import com.xgx.springboot.service.UserServiceOuter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/4/9 11:43 <br/>
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class MyTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserServiceOuter outerService;

    @Test
    public void testUserMapper(){
        List<User> allUsers = userMapper.getAllUsers();
        System.out.println(allUsers);
        userMapper.addUser(new User("第一人", "第一地址"));
    }

    @Test
    public void testTransaction(){

        outerService.invokeInner();
    }

}
