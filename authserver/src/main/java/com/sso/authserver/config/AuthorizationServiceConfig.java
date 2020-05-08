package com.sso.authserver.config;

import com.sso.authserver.service.MyClientDetailsService;
import com.sso.authserver.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer //开启授权服务
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    //认证方式管理器 表示支持password认证模式
    @Autowired
    AuthenticationManager authenticationManager;

    //redis 连接工厂 登陆成功后的token需要存在redis里面，因为redis里面有过期机制
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //用户详细信息服务 里面存放着用户信息
    @Autowired
    MyUserService userDetailsService;

    @Resource
    private MyClientDetailsService myClientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(myClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                //身份认证管理
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端表单身份验证
        security
                ///oauth/check_token 端点你需要在授权服务将这个端点暴露出去，以便资源服务可以进行访问，
                .tokenKeyAccess("permitAll()")// /oauth/token_key 安全配置
                .checkTokenAccess("isAuthenticated()") // /oauth/check_token 安全配置
                .allowFormAuthenticationForClients();
    }


//    /oauth/authorize ：申请授权码 code, 涉及的类 AuthorizationEndpoint
//  /oauth/token ：获取令牌 token, 涉及的类 TokenEndpoint
//  /oauth/check_token ：用于资源服务器请求端点来检查令牌是否有效, 涉及的类 CheckTokenEndpoint
//  /oauth/confirm_access ：用户确认授权提交, 涉及的类 WhitelabelApprovalEndpoint
//  /oauth/error ：授权服务错误信息, 涉及的类 WhitelabelErrorEndpoint
//  /oauth/token_key ：提供公有密匙的端点，使用 JWT 令牌时会使用 , 涉及的类 TokenKeyEndpoint


}
