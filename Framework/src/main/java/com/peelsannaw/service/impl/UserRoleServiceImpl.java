package com.peelsannaw.service.impl;

import com.peelsannaw.entity.UserRole;
import com.peelsannaw.mapper.UserRoleMapper;
import com.peelsannaw.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
