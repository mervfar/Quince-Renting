package com.quince.rentingapp.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/**").and()
                .authorizeRequests().antMatchers("/register").permitAll().and()
                .authorizeRequests().antMatchers("/avis/**").permitAll().and()
                .authorizeRequests().antMatchers("/deployment").permitAll().and()
                .authorizeRequests().antMatchers("/api/**").authenticated().and();

        http.headers().frameOptions().disable();

    }
}