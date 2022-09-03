package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.User;
import com.peelsannaw.service.LoginService;
import com.peelsannaw.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CommonController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UploadService uploadService;

    /**
     * 后期考虑改成DTO方式登录 因为登录仅仅只需要账号密码即可 不需要其他的
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Res<?> login(@RequestBody User user){
        return loginService.login(user);
    }
    @PostMapping("/logout")
    public Res<?> logOut(){
        return loginService.logOut();
    }

    @PostMapping("/upload")
    public Res<?> upload(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
