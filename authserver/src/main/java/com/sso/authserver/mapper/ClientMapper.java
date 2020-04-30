package com.sso.authserver.mapper;

import com.sso.authserver.entity.ClientInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.oauth2.provider.ClientDetails;

@Mapper
public interface ClientMapper {

    @Select("select client_id as clientId," +
            "secret,resource_ids as resourceIds," +
            "auth_types as authTypes," +
            "access_token_validity_seconds as accessTokenValiditySeconds ," +
            "refresh_token_validity_seconds as refreshTokenValiditySeconds," +
            "scopes," +
            "auto_approve autoApprove," +
            "redirect_uris as redirectUris," +
            "authorities " +
            " from t_client_info where client_id = #{clientId}")
    ClientInfo loadClientByClientId(String clientId);

}
