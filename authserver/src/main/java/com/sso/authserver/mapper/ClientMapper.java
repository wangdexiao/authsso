package com.sso.authserver.mapper;

import com.sso.authserver.entity.ClientInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.List;

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

    @Select("select client_id as clientId," +
            "client_secret as secret,resource_ids as resourceIds," +
            "authorized_grant_types as authTypes," +
            "access_token_validity as accessTokenValiditySeconds ," +
            "refresh_token_validity as refreshTokenValiditySeconds," +
            "scope as scopes," +
            "autoapprove as autoApprove," +
            "web_server_redirect_uri as redirectUris," +
            "authorities " +
            " from oauth_client_details ")
    List<ClientInfo> getClients();

    @Insert({ "insert into oauth_client_details( client_id, resource_ids, client_secret, scope,authorized_grant_types,web_server_redirect_uri,access_token_validity,refresh_token_validity,autoapprove) " +
            "values( #{clientId}, #{resourceIds}, #{secret}, #{scopes},#{authTypes},#{redirectUris},#{accessTokenValiditySeconds},#{refreshTokenValiditySeconds},#{autoApprove})" })
    int addClient(ClientInfo clientInfo);

    @Delete("delete from oauth_client_details where client_id = #{clientId}")
    int delClientById(String clientId);
}
