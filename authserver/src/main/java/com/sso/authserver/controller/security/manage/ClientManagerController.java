package com.sso.authserver.controller.security.manage;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sso.authserver.entity.OauthClientDetails;
import com.sso.authserver.entity.Result;
import com.sso.authserver.service.IOauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/clients")
@Controller
public class ClientManagerController {


    @Autowired
    private IOauthClientDetailsService clientDetailsService;

    @GetMapping({"/",""})
    public String getClients(Map<String,Object> map){
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
    public Result getClients(OauthClientDetails client){
        int count = clientDetailsService.count(Wrappers.<OauthClientDetails>lambdaQuery().eq(OauthClientDetails::getId, client.getId()));
        boolean save;
        if(count > 0){
            save = clientDetailsService.updateById(client);
        }else {
            save = clientDetailsService.save(client);
        }
        if(save){
            return Result.<String>sucess("添加客户端成功");
        }else {
            return Result.<String>fail("添加客户端失败",null);
        }
    }


    @ResponseBody
    @RequestMapping(path = "/del",method = {RequestMethod.POST,RequestMethod.GET})
    public Result delClient( OauthClientDetails clientInfo){
        boolean b = clientDetailsService.removeById(clientInfo.getId());
        if (b) {
            return Result.<String>sucess("删除客户端成功");
        }else {
            return Result.<String>fail("删除客户端失败",null);
        }
    }

}
