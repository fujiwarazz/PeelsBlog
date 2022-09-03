package com.peelsannaw.utils;

import com.peelsannaw.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static LoginUser getLoginUser(){
        return (LoginUser) getAuth().getPrincipal();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id!=null&&1L==id;
    }
    public static Long getUserId(){
        return getLoginUser().getUser().getId();
    }
}
