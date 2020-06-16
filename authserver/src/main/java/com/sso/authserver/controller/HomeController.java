package com.sso.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class HomeController {




    @PreAuthorize("hasAuthority('sys:manage')")
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public ModelAndView homePage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userInfo = (User) authentication.getPrincipal();
        String username = userInfo.getUsername();
//        Object credentials = authentication.getCredentials();
//        Object details = authentication.getDetails();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", username);
        return modelAndView;
    }
}
