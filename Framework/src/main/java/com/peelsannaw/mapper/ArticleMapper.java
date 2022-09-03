package com.peelsannaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peelsannaw.entity.Article;
import com.peelsannaw.entity.Category;
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
public interface ArticleMapper extends BaseMapper<Article> {
    List<Long> getUsefulIds();
}
