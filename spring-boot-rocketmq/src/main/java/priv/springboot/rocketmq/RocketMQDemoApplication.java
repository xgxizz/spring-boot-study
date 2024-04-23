package priv.springboot.rocketmq;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xgx
 */
@SpringBootApplication
public class RocketMQDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQDemoApplication.class, args);
    }

}
