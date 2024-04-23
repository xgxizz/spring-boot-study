package priv.springboot.rocketmq.controller;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: xu.gx
 * @Date: 2024/3/28 10:49
 **/
@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/send")
    public String sendMessage(String message) {
        rocketMQTemplate.convertAndSend("test", message);
        return "success";
    }


    // 监听消息
    @RocketMQMessageListener(topic = "test-topic", consumerGroup = "test-group", consumeMode = ConsumeMode.CONCURRENTLY)
    public class MyConsumer implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("Received message: " + message);
        }
    }
}
