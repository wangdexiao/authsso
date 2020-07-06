package com.sso.authserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.entity.OauthClientDetails;
import com.sso.authserver.mapper.ClientMapper;
import com.sso.authserver.mapper.OauthClientDetailsMapper;
import com.sso.authserver.service.IOauthClientDetailsService;
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
    private IOauthClientDetailsService clientDetailsService;

    @GetMapping("/")
    public String getClients(Map<String,Object> map){
        List<OauthClientDetails> clientDetails = clientDetailsService.list();
        map.put("clients", clientDetails);
        return "clientmanage";
    }

    @GetMapping("/add")
    public String getClients(Model model,OauthClientDetails client){
        log.info("请求的客户端信息:" + client.toString());
        clientDetailsService.save(client);
        List<OauthClientDetails> clients = clientDetailsService.list();
        model.addAttribute("clients", clients);
        return "clientmanage";
    }
}
