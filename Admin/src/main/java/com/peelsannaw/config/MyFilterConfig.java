package com.peelsannaw.config;

import com.peelsannaw.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class MyFilterConfig{

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Bean
    public FilterRegistrationBean<OncePerRequestFilter>addFilter(){
        FilterRegistrationBean<OncePerRequestFilter>filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthenticationTokenFilter);

        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setName("jwtAuthenticationFilter");
        return filterRegistrationBean;
    }


}
