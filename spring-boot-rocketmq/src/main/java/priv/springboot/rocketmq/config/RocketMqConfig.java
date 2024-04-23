package priv.springboot.rocketmq.config;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: xu.gx
 * @Date: 2024/3/28 10:43
 **/
@Configuration
public class RocketMqConfig {
    // 定义 RocketMQTemplate Bean
    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        return new RocketMQTemplate();
    }
}
