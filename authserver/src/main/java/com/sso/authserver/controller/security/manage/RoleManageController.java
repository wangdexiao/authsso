package com.sso.authserver.controller.security.manage;

import com.sso.authserver.entity.SysRole;
import com.sso.authserver.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/roles")
@Controller
public class RoleManageController {


    @Autowired
    private ISysRoleService roleService;

    @GetMapping({"/",""})
    public String rolesPage(Model model){

        List<String> roleTitleList = getRoleTitle();
        List<SysRole> roleList = roleService.list();
        model.addAttribute("roleTitles", roleTitleList);
        model.addAttribute("roles", roleList);
        return "rolesManage";
    }

    private List<String> getRoleTitle() {
        List<String> roleTitle = new ArrayList<>();
        roleTitle.add("名称");
        roleTitle.add("说明");
        roleTitle.add("操作");
        return roleTitle;
    }
}
