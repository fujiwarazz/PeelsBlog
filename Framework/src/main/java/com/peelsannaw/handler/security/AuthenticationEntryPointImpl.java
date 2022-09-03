package com.peelsannaw.handler.security;

import com.alibaba.fastjson.JSON;
import com.peelsannaw.common.Res;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
/**
 * 认证失败处理器
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        Res result = null;
        //TODO:根据情况判断返回类型
        if(e instanceof BadCredentialsException){
            result = Res.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),e.getMessage());
        }
        else if(e instanceof InsufficientAuthenticationException){
            result = Res.errorResult(AppHttpCodeEnum.NEED_LOGIN.getCode(),"123");
        }else{
            result = Res.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(response,JSON.toJSONString(result));
    }
}
