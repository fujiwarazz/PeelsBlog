package com.peelsannaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Comment;
import com.peelsannaw.entity.User;
import com.peelsannaw.mapper.CommentMapper;
import com.peelsannaw.mapper.UserMapper;
import com.peelsannaw.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peelsannaw.service.IUserService;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.utils.SecurityUtils;
import com.peelsannaw.vo.CommentDetailVo;
import com.peelsannaw.vo.CommentVo;
import com.peelsannaw.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    public IUserService userService;

    @Override
    public Res<?> sendComment(Comment comment) {
        this.save(comment);
        return Res.okResult();
    }

    @Override
    public Res<?> getCommentByAid(String type,Integer pageNum, Integer pageSize, Long articleId) {
        //首先获取根
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq("0".equals(type),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getType,type);
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        //分页
        List<Comment> records = page(new Page<>(pageNum, pageSize), queryWrapper).getRecords();
        List<CommentVo> commentVos = toCommentVoList(records);
        //查询子评论
        for (CommentVo commentVo : commentVos) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return Res.okResult(new PageVo(commentVos, list(queryWrapper).size()));
    }

    public List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        System.out.println(id);
        queryWrapper.eq(Comment::getRootId, id);
        List<Comment> comments = list(queryWrapper);

        return toCommentVoList(comments);
    }

    public List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            User user = userService.getById(commentVo.getCreateBy());
            commentVo.setUsername(user.getNickName());
            if (commentVo.getToCommentId() != -1) {
                String uid = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(uid);
            }
        }
        return commentVos;
    }
}