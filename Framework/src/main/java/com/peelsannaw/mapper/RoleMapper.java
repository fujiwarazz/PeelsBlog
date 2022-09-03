package com.peelsannaw.mapper;

import com.peelsannaw.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapper;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
