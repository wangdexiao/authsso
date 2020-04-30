package com.sso.authserver.controller;

import com.sso.authserver.entity.UserInfo;
import com.sso.authserver.mapper.TestMapper;
import com.sso.authserver.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    TestMapper testMapper;

    @Resource
    UserMapper userMapper;

    @GetMapping("/test")
    public UserInfo count(){
//        return testMapper.selectCount();
        UserInfo admin = userMapper.loadUserByUsername("admin");
        return admin;
    }
}
