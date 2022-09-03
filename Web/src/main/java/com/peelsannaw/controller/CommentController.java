package com.peelsannaw.controller;

import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Comment;
import com.peelsannaw.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论接口")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "评论列表",notes = "获取一页评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum",value = "页码"),
                       @ApiImplicitParam(name = "pageSize",value = "页码大小"),
                       @ApiImplicitParam(name = "articleId" ,value="文章Id")})
    @GetMapping("/commentList")
    public Res<?> getComments(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam Long articleId){
        log.info("pageINfo:{}",pageNum);
        return commentService.getCommentByAid("0",pageNum,pageSize,articleId);
    }

    @PostMapping
    public Res<?>sendComment(@RequestBody Comment comment){
        System.out.println("???????????ss");
        return commentService.sendComment(comment);
    }

    @GetMapping("/linkCommentList")
    public Res<?> getCommentListOfLink(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        return commentService.getCommentByAid("1",pageNum,pageSize,null);
    }
}
