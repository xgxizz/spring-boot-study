package com.xgx.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xgx
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/getTest")
    public void getTest(int num) throws Exception {
        log.info("{} 接受到请求:num={}", Thread.currentThread().getName(), num);
        TimeUnit.HOURS.sleep(1);
    }


    @GetMapping("/test")
    public Map getTest() throws Exception {
        // {
        //     avatarUrl:
        //     'https://we-retail-static-1300977798.cos.ap-guangzhou.myqcloud.com/retail-ui/components-exp/avatar/avatar-1.jpg',
        //             nickName: 'TDesign 🌟',
        //         phoneNumber: '13438358888',
        //         gender: 2,
        // }
        Map<String, Object> map = new HashMap<>();
        map.put("avatarUrl", "https://we-retail-static-1300977798.cos.ap-guangzhou.myqcloud.com/retail-ui/components-exp/avatar/avatar-1.jpg");
        map.put("nickName", "xgx");
        map.put("phoneNumber", "13303143929");
        map.put("gender", 2);
        return map;
    }
}