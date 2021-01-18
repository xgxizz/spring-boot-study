package com.xgx.springboot.controller;

import com.xgx.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/1/15 18:34 <br/>
 */
@RestController
public class DemoWebController {

    @Autowired
    private DemoService demoService;

    @RequestMapping
    public String test(){
        demoService.connection();
        return "ok";
    }
}
