package com.sso.authserver.controller;

import com.sso.authserver.entity.RolePermission;
import com.sso.authserver.entity.UserInfo;
import com.sso.authserver.mapper.UserMapper;
import com.sso.authserver.service.MyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasAuthority('sys:manage')")
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String homePage(Map<String,Object> attributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User temp = (User) authentication.getPrincipal();
        String username = temp.getUsername();
        UserInfo userInfo = userMapper.loadUserByUsername(username);
        List<RolePermission> permissions = userInfo.getPermissions();
        log.info("权限列表:" + permissions.toString());
        attributes.put("username", username);
        attributes.put("content", "index");
        return "index";
    }
}
