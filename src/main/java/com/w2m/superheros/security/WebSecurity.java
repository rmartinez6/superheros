package com.w2m.superheros.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/superheros/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/superheros/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/superheros/**").hasRole("ADMIN")
                .and().csrf().disable().headers().frameOptions().disable();
    }
}

