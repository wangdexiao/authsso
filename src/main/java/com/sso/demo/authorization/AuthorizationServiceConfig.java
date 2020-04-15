package com.sso.demo.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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
    UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_id")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(60 * 60 * 2)
                .resourceIds("rid")
                .scopes("all")
                .secret("$2a$10$jXQ72CKVIA5G.hhkZfBTa.fAxE.7QStwz85AxQImM1OtiFSO2YXpy");
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
        security.allowFormAuthenticationForClients();
    }
}
