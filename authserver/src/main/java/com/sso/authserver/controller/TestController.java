package com.sso.authserver.controller;

import com.sso.authserver.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestMapper testMapper;

    @GetMapping("/test")
    public Integer count(){
        return testMapper.selectCount();
    }
}
