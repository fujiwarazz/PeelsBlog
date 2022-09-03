package com.peelsannaw.mapper;

import com.peelsannaw.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
