package com.sso.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/loginpage").setViewName("loginpage");
        registry.addViewController("/error").setViewName("error");
//        registry.addViewController("/home").setViewName("UserManager");
    }
}
