package com.peelsannaw.service;

import com.peelsannaw.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
