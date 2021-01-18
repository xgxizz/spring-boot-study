package com.xgx.springboot.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: <br/>
 * 对应配置信息中的配置字段
 * @author: xgx <br/>
 * date: 2021/1/15 16:04 <br/>
 */
@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    private String url;
    private String driver;
    private String username;
    private String password;
}
