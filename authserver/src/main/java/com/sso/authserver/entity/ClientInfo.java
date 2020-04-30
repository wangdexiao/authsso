package com.sso.authserver.entity;

import lombok.Data;

@Data
public class ClientInfo {


    private String clientId;
    private String secret;
    private String resourceIds;
    private String authTypes;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
    private String scopes;
    private Boolean autoApprove;
    private String redirectUris;
    private String authorities;
}
