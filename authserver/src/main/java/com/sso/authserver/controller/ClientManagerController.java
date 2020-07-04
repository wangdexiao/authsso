package com.sso.authserver.controller;

import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.entity.Result;
import com.sso.authserver.mapper.ClientMapper;
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
    private ClientMapper clientMapper;

    @GetMapping("/")
    public String getClients(Map<String,Object> map){
        List<ClientInfo> clients = clientMapper.getClients();
        map.put("clients", clients);
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
        log.info("请求的客户端信息:" + client.toString());
        int i = clientMapper.addClient(client);
        log.info("添加id返回值:" + i);
//        List<ClientInfo> clients = clientMapper.getClients();
//        model.addAttribute("clients", clients);
        return Result.<String>sucess("添加客户端成功");
    }


    @ResponseBody
    @RequestMapping(path = "/del",method = {RequestMethod.POST,RequestMethod.GET})
    public Result delClient( ClientInfo clientInfo){
        int i = clientMapper.delClientById(clientInfo.getClientId());
        return Result.<String>sucess("删除客户端成功");
    }

}
