package com.sso.authserver.controller;

import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.entity.UserInfo;
import com.sso.authserver.mapper.ClientMapper;
import com.sso.authserver.mapper.TestMapper;
import com.sso.authserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {

    @Resource
    TestMapper testMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    private ClientMapper clientMapper;

    @GetMapping("/test")
    public ClientInfo count(){
//        return testMapper.selectCount();
//        UserInfo admin = userMapper.loadUserByUsername("admin");
        ClientInfo clientInfo = clientMapper.loadClientByClientId("client_id");
        return clientInfo;
    }
}
