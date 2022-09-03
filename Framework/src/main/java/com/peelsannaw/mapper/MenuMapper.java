package com.peelsannaw.mapper;

import com.peelsannaw.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-08-15
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
