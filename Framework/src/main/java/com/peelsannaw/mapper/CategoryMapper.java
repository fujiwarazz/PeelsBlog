package com.peelsannaw.mapper;

import com.peelsannaw.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
