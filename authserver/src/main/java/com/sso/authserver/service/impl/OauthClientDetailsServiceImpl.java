package com.sso.authserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.authserver.entity.OauthClientDetails;
import com.sso.authserver.mapper.OauthClientDetailsMapper;
import com.sso.authserver.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wadexi
 * @since 2020-07-06
 */
@Service
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {


}
