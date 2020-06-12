package com.sso.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageManagerController {

    @GetMapping("/home")
    public ModelAndView homePage(String page,String conent){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("UserManager");
        modelAndView.addObject("page", "common/footer");
        modelAndView.addObject("content", "copy");
        return modelAndView;
    }

    @GetMapping("/users/manager/page")
    public ModelAndView usersManagerPage(String page,String conent){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("UserManager");
        modelAndView.addObject("page", "users");
        modelAndView.addObject("content", "users-manager");
        return modelAndView;
    }


}
