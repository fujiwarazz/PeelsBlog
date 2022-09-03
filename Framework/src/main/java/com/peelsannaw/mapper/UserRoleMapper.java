package com.peelsannaw.mapper;

import com.peelsannaw.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
