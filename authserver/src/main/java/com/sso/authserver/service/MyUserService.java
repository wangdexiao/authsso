package com.sso.authserver.service;

import com.sso.authserver.entity.Role;
import com.sso.authserver.entity.RolePermission;
import com.sso.authserver.entity.UserInfo;
import com.sso.authserver.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = userMapper.loadUserByUsername(userName);

        String[] roles = new String[userInfo.getRole().size()];
        userInfo.getRole().stream().map(Role::getName).collect(Collectors.toList()).toArray(roles);

        String[] authorities = new String[userInfo.getPermissions().size()];
        userInfo.getPermissions().stream().map(RolePermission::getCode).collect(Collectors.toList()).toArray(authorities);
        return User.builder()
                .username(userName)
                .roles(roles)
                .authorities(authorities)
                .password(userInfo.getPassword())
                .build();

    }
}
