package com.xgx.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xgx
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
    @RequestMapping("/login")
    public String login(String username, String password) {
        // 获取主题对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            System.out.println("登录成功！！！");
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户错误！！！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
        }
        return "redirect:/login.jsp";
    }
}
