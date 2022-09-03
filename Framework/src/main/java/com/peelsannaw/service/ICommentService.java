package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
public interface ICommentService extends IService<Comment> {

    Res<?> getCommentByAid(String type,Integer pageNum, Integer pageSize, Long articleId);

    Res<?> sendComment(Comment comment);

 }
