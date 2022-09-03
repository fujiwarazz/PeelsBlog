package com.peelsannaw.service.impl;

import com.peelsannaw.entity.Role;
import com.peelsannaw.mapper.RoleMapper;
import com.peelsannaw.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
