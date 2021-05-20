package com.jdbl_12_oauth.oauth2.authserver;

import com.jdbl_12_oauth.oauth2.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class Authconf  extends  AuthorizationServerConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Bean
    public TokenStore tokenStore()
    {
        return  new JdbcTokenStore(dataSource);
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager manager;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }
     @Autowired
    UserManager userManager;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
         endpoints.tokenStore(tokenStore())
                 .authenticationManager(manager)
                 .userDetailsService(userManager);
    }
}
