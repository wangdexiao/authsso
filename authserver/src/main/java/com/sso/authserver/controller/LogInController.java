package com.sso.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogInController {

    @GetMapping("/loginpage")
    public ModelAndView loginpage(){
        ModelAndView modelAndView =
                new ModelAndView("loginpage", "title", "统一登录中心");
        return modelAndView;
    }
}
