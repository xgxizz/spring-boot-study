package com.xgx.springboot.config;

import com.xgx.springboot.entity.DemoProperties;
import com.xgx.springboot.service.DemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Description: <br/>
 * @author: xgx <br/>
 * date: 2021/1/15 16:15 <br/>
 */
@Configuration
//@EnableConfigurationProperties 注解。该注解是用来开启@ConfigurationProperties注解配置Bean的支持。
//也就是@EnableConfigurationProperties注解告诉Spring Boot 能支持@ConfigurationProperties。
@EnableConfigurationProperties(DemoProperties.class)
//@ConditionalOnProperty 指定属性不存在时是否应用配置。默认为false，即属性缺失时不应用配置。
@ConditionalOnProperty(prefix = "demo", name = "on", havingValue = "true",matchIfMissing = true)
public class DemoConfig {

    @Bean(name = "demoService")
    public DemoService demoService(DemoProperties demoProperties) {
        return new DemoService(demoProperties.getUrl(),
                demoProperties.getDriver(),
                demoProperties.getUsername(),
                demoProperties.getPassword()
        );
    }
}
