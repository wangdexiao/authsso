package com.sso.authserver.controller;

import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/clients")
@Controller
public class ClientManagerController {


    @Autowired
    private ClientMapper clientMapper;

    @GetMapping("/")
    public String getClients(Map<String,Object> map){
        ClientInfo clients = clientMapper.getClients();
        map.put("clients", clients);
        return "clientmanage";
    }
}
