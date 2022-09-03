package com.peelsannaw.filter;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.LoginUser;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.utils.JwtUtil;
import com.peelsannaw.utils.RedisCache;
import com.peelsannaw.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//过滤器不依赖于spring容器
@Component
@Slf4j
 public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    //因为过滤器初始化是在bean初始化之前的 所以如果要在过滤器内使用bean就必须将过滤器注册成一个bean
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头里的token
        String token = httpServletRequest.getHeader("token");
        if(StringUtils.isEmpty(token)){
            //说明该接口不需要登陆 直接放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析token获得userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);

        } catch (Exception e) {
            e.printStackTrace();
            //token超时或者非法
            Res<?> res = Res.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(res));
            return;
        }
        String userId = claims.getSubject();
        //从redis得到用户信息
        LoginUser loginUser = redisCache.getCacheObject("login:"+userId);
        //如果redis中没有
        if(Objects.isNull(loginUser)){
            Res<?> res = Res.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(res));
            return;
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
