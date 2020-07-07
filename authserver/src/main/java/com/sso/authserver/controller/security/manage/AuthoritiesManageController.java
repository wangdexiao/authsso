package com.sso.authserver.controller.security.manage;

import com.sso.authserver.entity.SysPermission;
import com.sso.authserver.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/authorties")
@Controller
public class AuthoritiesManageController {


    @Autowired
    private ISysPermissionService permissionService;

    @GetMapping(path = {"/",""})
    public String authortiesPage(Model model){

        List<SysPermission> permissionList = permissionService.list();

        List<String> permissionTitleList = getPermissionTitle();
        model.addAttribute("permissions", permissionList);
        model.addAttribute("permissionTitles", permissionTitleList);

        return "authortiesManage";
    }

    private List<String> getPermissionTitle() {
        List<String> permissionTitle = new ArrayList<>();
        permissionTitle.add("权限id");
        permissionTitle.add("权限名称");
        permissionTitle.add("权限标识符");
        permissionTitle.add("授权路径");
        permissionTitle.add("类型");
        permissionTitle.add("图标");
        permissionTitle.add("备注");
        permissionTitle.add("操作");
        return permissionTitle;
    }
}
