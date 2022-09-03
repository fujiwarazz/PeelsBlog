package com.peelsannaw.service.impl;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.LoginUser;
import com.peelsannaw.entity.User;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.exception.SystemException;
import com.peelsannaw.service.LoginService;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.utils.JwtUtil;
import com.peelsannaw.utils.RedisCache;
import com.peelsannaw.vo.LoginDetailVo;
import com.peelsannaw.vo.UserInfoVo;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class LoginServiceImpl  implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public Res<?> login(User user) {

        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //authenticationManager会调用UserDetailService
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否通过验证
        if(Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //获取Userid生成Token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //将用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        //将token和userInfo封装返回
        LoginDetailVo vo = new LoginDetailVo();
        vo.setToken(jwt);
        vo.setUserInfo(BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class));
        return Res.okResult(vo);
    }

    @Override
    public Res<?> logOut() {
        //删除redis的用户信息
        //从token获取userId
        //在过滤器内封装了token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userID = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:"+userID);
        return Res.okResult();
    }
}
