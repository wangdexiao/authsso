package com.sso.authserver.controller;

import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
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

    @GetMapping("/add")
    public String getClients(Model model,ClientInfo client){
        log.info("请求的客户端信息:" + client.toString());
        ClientInfo clientInfo = clientMapper.addClient(client);
        ClientInfo clients = clientMapper.getClients();
        model.addAttribute("clients", clients);
        return "clientmanage";
    }
}
