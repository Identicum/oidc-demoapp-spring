package com.identicum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest -> authorizeRequest
                .antMatchers("/", "/webjars/**", "/css/**", "/favicon.*", "/imgs/**").permitAll()
                .anyRequest().authenticated())
            .oauth2Login(oauthLogin -> oauthLogin
                        .userInfoEndpoint()
                        .oidcUserService(new OidcUserService()));
    }
}
