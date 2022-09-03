package com.peelsannaw.service.impl;

import com.peelsannaw.entity.RoleMenu;
import com.peelsannaw.mapper.RoleMenuMapper;
import com.peelsannaw.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
