package com.sso.authserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.entity.Result;
import com.sso.authserver.entity.OauthClientDetails;
import com.sso.authserver.mapper.ClientMapper;
import com.sso.authserver.mapper.OauthClientDetailsMapper;
import com.sso.authserver.service.IOauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        List<ClientInfo> clients = clientMapper.getClients();
        map.put("clients", clients);
        List<OauthClientDetails> clientDetails = clientDetailsService.list();
        map.put("clients", clientDetails);
        return "clientmanage";
    }

    /**
     * 客户端添加
     * @param client
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add",method = {RequestMethod.POST,RequestMethod.GET})
    public Result getClients(/*Model model,*/ClientInfo client){
    @GetMapping("/add")
    public String getClients(Model model,OauthClientDetails client){
        log.info("请求的客户端信息:" + client.toString());
        int i = clientMapper.addClient(client);
        log.info("添加id返回值:" + i);
//        List<ClientInfo> clients = clientMapper.getClients();
//        model.addAttribute("clients", clients);
        return Result.<String>sucess("添加客户端成功");
        clientDetailsService.save(client);
        List<OauthClientDetails> clients = clientDetailsService.list();
        model.addAttribute("clients", clients);
        return "clientmanage";
    }


    @ResponseBody
    @RequestMapping(path = "/del",method = {RequestMethod.POST,RequestMethod.GET})
    public Result delClient( ClientInfo clientInfo){
        int i = clientMapper.delClientById(clientInfo.getClientId());
        return Result.<String>sucess("删除客户端成功");
    }

}
