package com.sso.authserver.entity;

import lombok.Data;

import java.util.List;

/**
 * 角色
 */
@Data
public class Role {

    private String roleName;
    private String description;

    private List<RolePermission> permissionList;
}
