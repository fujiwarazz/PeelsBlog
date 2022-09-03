package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Menu;
import com.peelsannaw.entity.User;
import com.peelsannaw.mapper.UserMapper;
import com.peelsannaw.service.AdminLoginService;
import com.peelsannaw.service.IMenuService;
import com.peelsannaw.service.IUserService;
import com.peelsannaw.utils.SecurityUtils;
import com.peelsannaw.vo.UserRouterVo;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @author 27365
 */
@RestController
public class userController {


    private final AdminLoginService adminLoginService;
    private final IUserService userService;
    private final IMenuService menuService;
    public userController(AdminLoginService adminLoginService, IUserService userService,IMenuService menuService ) {
        this.adminLoginService = adminLoginService;
        this.userService = userService;
        this.menuService = menuService;
    }




    @GetMapping("/getInfo")
    public Res<?>getInfo(){
        return userService.getInfo();
    }
    @PostMapping("/user/login")
    public Res<?> login(@RequestBody User user){


        return adminLoginService.login(user);
    }

    @GetMapping("/getRouters")
    public Res<?> getRouters(){

//        Long userId = SecurityUtils.getUserId();
//        //查询menu 结果是tree的形式
//        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
//        //封装数据返回
//        return Res.okResult(new UserRouterVo(menus));
            return userService.getRouters();
    }

}
