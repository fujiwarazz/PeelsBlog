package com.peelsannaw.service.impl;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.LoginUser;
import com.peelsannaw.entity.User;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.service.AdminLoginService;
import com.peelsannaw.utils.JwtUtil;
import com.peelsannaw.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public Res<?> login(User user) {
        if (Objects.isNull(user.getUserName())){
            return Res.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            return Res.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        System.out.println("===========================");
        System.out.println(loginUser.getUser().getUserName());
        User user1 = loginUser.getUser();
        //生成jwt
        String jwt = JwtUtil.createJWT(user1.getId().toString());
        redisCache.setCacheObject("adminLogin:"+user1.getId(),loginUser);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return Res.okResult(map);
    }
}
