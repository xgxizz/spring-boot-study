package com.xgx.springboot.controller;

import com.xgx.springboot.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private AsyncService asyncService;

    @RequestMapping("/submit")
    public String submit(){
        logger.info("start submit");

        //调用service层的任务
        asyncService.executeAsync();

        logger.info("end submit");

        return "success";
    }
}