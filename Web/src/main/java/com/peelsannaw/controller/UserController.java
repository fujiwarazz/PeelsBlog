package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.service.IUserService;
import com.peelsannaw.vo.UserInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @GetMapping("/userInfo")
    public Res<?>getUerInfo(){
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    public Res<?> updateUserInfo(@RequestBody UserInfoVo vo){
        return userService.updateUser(vo);
    }
}
