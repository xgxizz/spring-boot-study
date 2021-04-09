package com.xgx.springboot.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/1/28 11:36 <br/>
 */
@Service
public class UserService implements BeanNameAware, BeanFactoryAware {

    private String beanName;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    public String getBeanName() {
        return beanName;
    }

    public void test(){
        System.out.println("test......");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
