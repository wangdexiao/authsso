package com.sso.reserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {



//    /**
//     * jwt令牌
//     */
    private static final String SIGNING_KEY = "my-secret";

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        生成密钥的证书文件 每个证书包含公钥和私钥
//        keytool -genkeypair -alias oauth2 -keyalg RSA -keypass oauth2 -keystore oauth2.jks
//                -storepass oauth2
        //读取oauth2.jks文件中的私钥，第二个参数未口令oauth2
//        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        //别名
//        converter.setKeyPair(keyFactory.getKeyPair("oauth2"));
//        对称密钥
//        对称密钥来签署我们的令牌，资源服务器也将使用此密钥来验证准确性
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Bean
    public TokenStore tokenStore(){
        //redis 管理令牌
//        return new RedisTokenStore(RedisConnectionFactory)
        //jdbc管理令牌
//        return new JdbcTokenStore(dataSource());
        //jwt 管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }



}
