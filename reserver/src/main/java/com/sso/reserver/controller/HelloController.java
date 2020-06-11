package com.sso.reserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin,我就是你的userinfo";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "hello user";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/product/list")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public List<String> list(){
        List<String> list = new ArrayList<>();
        list.add("眼镜");
        list.add("格子衬衣");
        list.add("双肩包");
        return list;
    }

}
