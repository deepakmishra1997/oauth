package com.jdbl_12_oauth.oauth2.resourceserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

public class ResourceConf extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
        .authorizeRequests()
        .antMatchers("/**")
        .access("#oauth2.hasAnyScope('read','write')");
    }
}
