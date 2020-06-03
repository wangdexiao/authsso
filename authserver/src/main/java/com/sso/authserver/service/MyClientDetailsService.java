package com.sso.authserver.service;

import com.sso.authserver.entity.ClientInfo;
import com.sso.authserver.mapper.ClientMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MyClientDetailsService implements ClientDetailsService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ClientInfo clientInfo = clientMapper.loadClientByClientId(clientId);
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        String secret = clientInfo.getSecret();
        String[] resourceIds = clientInfo.getResourceIds().split(",");
        String[] authTypes = clientInfo.getAuthTypes().split(",");
        int accessTokenValiditySeconds = clientInfo.getAccessTokenValiditySeconds();
        int refreshTokenValiditySeconds = clientInfo.getRefreshTokenValiditySeconds();
        String[] scopes = clientInfo.getScopes().split(",");
        Boolean autoApprove = clientInfo.getAutoApprove();
        String[] rediectUris = clientInfo.getRedirectUris().split(",");
        List<String> authorities = Arrays.asList(clientInfo.getAuthorities().split(","));

        baseClientDetails.setClientId(clientId);
        baseClientDetails.setClientSecret(secret);
        baseClientDetails.setResourceIds(Arrays.asList(resourceIds));
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(authTypes));
        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        baseClientDetails.setScope(Arrays.asList(scopes));
        if(autoApprove){
            baseClientDetails.setAutoApproveScopes(Arrays.asList(scopes));
        }
        baseClientDetails.setRegisteredRedirectUri(new HashSet<>(Arrays.asList(rediectUris)));
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        authorities.forEach(it -> grantedAuthorityList.add(new SimpleGrantedAuthority(it)));
        baseClientDetails.setAuthorities(grantedAuthorityList);
        return baseClientDetails;
    }
}
