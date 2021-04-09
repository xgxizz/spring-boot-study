package com.xgx.springboot.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/1/28 11:37 <br/>
 */
@Component
public class User implements BeanFactoryPostProcessor {
    private String usrName;
    private int age;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
