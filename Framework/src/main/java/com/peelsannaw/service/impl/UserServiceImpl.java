package com.peelsannaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.LoginUser;
import com.peelsannaw.entity.Menu;
import com.peelsannaw.entity.User;
import com.peelsannaw.mapper.UserMapper;
import com.peelsannaw.service.IMenuService;
import com.peelsannaw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.utils.SecurityUtils;
import com.peelsannaw.vo.MenuVo;
import com.peelsannaw.vo.UserInfoVo;
import com.peelsannaw.vo.UserPermissionVo;
import com.peelsannaw.vo.UserRouterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IMenuService menuService;

    @Override
    public Res<?> getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, userId);
        return Res.okResult(BeanCopyUtils.copyBean(this.getOne(queryWrapper), UserInfoVo.class));
    }

    @Override
    public Res<?> updateUser(UserInfoVo vo) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getAvatar, vo.getAvatar());
        wrapper.set(User::getNickName, vo.getNickName());
        wrapper.set(User::getSex, vo.getSex());
        return Res.okResult(update(wrapper));
    }

    @Override
    public Res<?> getInfo() {
        UserPermissionVo userPermissionVo = new UserPermissionVo();
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        Long id = user.getId();
        if (user.getId() == 1L) {
            roles.add("admin");

            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, "C", "F");
            queryWrapper.eq(Menu::getStatus, 0);
            List<Menu> list = menuService.list(queryWrapper);
            permissions = list.stream().map(Menu::getPerms).collect(Collectors.toList());
        } else {
            roles = getBaseMapper().getRoles(id);
            permissions = getBaseMapper().getPermissions(id);
        }
        userPermissionVo.setRoles(roles);
        userPermissionVo.setPermissions(permissions);
        userPermissionVo.setUser(userInfoVo);
        return Res.okResult(userPermissionVo);
    }

    @Override
    public Res<?> getRouters() {
        UserRouterVo userRouterVo = new UserRouterVo();
        List<Menu> menuVos = null;
        Long userId = SecurityUtils.getUserId();
        if (userId == 1L) {
           menuVos = getAllRouters();
        } else {
            menuVos = getRoutersById(userId);
        }

        menuVos = menuVos2TreeMenuVos(menuVos);
        userRouterVo.setRouters(menuVos);
        return Res.okResult(userRouterVo);
    }


    public List<Menu> menuVos2TreeMenuVos(List<Menu>menuVos){
        return menuVos.stream()
                .filter(m -> m.getParentId()==0L)
                .peek(menu -> menu.setChildren(getChildren(menu,menuVos)))
                .collect(Collectors.toList());
    }

    private List<Menu> getChildren(Menu menuVo,List<Menu>menuVos) {
        return menuVos.stream()
                .filter(m -> m.getParentId().equals(menuVo.getId()))
                .peek(m-> m.setChildren(getChildren(m,menuVos)))
                .collect(Collectors.toList());
    }

    public List<Menu> getAllRouters() {
       return getBaseMapper().getAdminRouters();
     }

    public List<Menu> getRoutersById(Long id) {
        return getBaseMapper().getRoutersById(id);
    }
}