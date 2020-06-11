package com.sso.reserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer //表示开启资源服务
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法级别权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    private static final String RESOURCE_ID = "rid";

    @Resource
    private ResourceServerTokenServices tokenService;

//    //在资源 服务配置RemoteTokenServices
//    @Bean
//    public ResourceServerTokenServices tokenService() {
//        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
//        RemoteTokenServices service=new RemoteTokenServices();
//        service.setCheckTokenEndpointUrl("http://localhost:9000/oauth/check_token");
//        service.setClientId("sharecesuo");
//        service.setClientSecret("123456");
//        return service;
//    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                //spring security 不会创建也不会使用HttpSession
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .permitAll()
                .and()
                    .csrf()
                    .disable();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
//                .tokenStore(tokenStore)
                .tokenServices(tokenService)
                .stateless(true);
    }
}
