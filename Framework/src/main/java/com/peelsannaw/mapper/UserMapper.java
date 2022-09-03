package com.peelsannaw.mapper;

import com.peelsannaw.entity.Menu;
import com.peelsannaw.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> getPermissions(@Param("id")Long id);

    List<String> getRoles(@Param("id") Long id);

    List<Menu> getRoutersById(Long userId);

    List<Menu> getAdminRouters();
}
