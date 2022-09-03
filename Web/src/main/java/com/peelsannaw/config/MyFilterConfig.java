package com.peelsannaw.config;

import com.peelsannaw.filter.JwtAuthenticationTokenFilter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class MyFilterConfig{

//    @Autowired
//    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Bean
    public FilterRegistrationBean<OncePerRequestFilter>addFilter(){
        FilterRegistrationBean<OncePerRequestFilter>filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthenticationTokenFilter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setName("jwtAuthenticationFilter");

        return filterRegistrationBean;
    }


}
