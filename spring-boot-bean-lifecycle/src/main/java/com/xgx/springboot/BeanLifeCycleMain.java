package com.xgx.springboot;

import com.xgx.springboot.bean.UserService;
import com.xgx.springboot.config.AppConfig;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: <br/>
 * Bean的生命周期
 * @author: xgx <br/>
 * date: 2021/1/28 11:33 <br/>
 */
public class BeanLifeCycleMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        String beanName = userService.getBeanName();
        System.out.println(beanName);
        context.getBean("");
        //DefaultSingletonBeanRegistry
    }

}
