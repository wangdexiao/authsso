package com.sso.authserver.controller.security.manage;

import com.sso.authserver.entity.SysUser;
import com.sso.authserver.service.ISysUserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping({"/users"})
@Controller
public class UsersManageController {

    @Autowired
    private ISysUserService userService;

    @GetMapping({"/",""})
    public String usersPage(Model model){
        List<SysUser> userList = userService.list();
        model.addAttribute("users", userList);
        model.addAttribute("userTitle", getUserTitle());
        return "usersManage";
    }

    private List<String> getUserTitle() {
        List<String> userTitle = new ArrayList<>();
        userTitle.add("用户id");
        userTitle.add("用户名");
        userTitle.add("密码");
        userTitle.add("昵称");
        userTitle.add("注册手机号");
        userTitle.add("注册邮箱");
        userTitle.add("账户是否过期");
        userTitle.add("账户是否被锁定");
        userTitle.add("密码是否过期");
        userTitle.add("账户是否可用");
        userTitle.add("操作");
        return userTitle;
    }
}
