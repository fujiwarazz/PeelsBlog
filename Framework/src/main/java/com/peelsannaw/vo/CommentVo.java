package com.peelsannaw.vo;

import com.peelsannaw.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo{
    private Long articleId;
    private List<CommentVo> children;
    private String content;
    private Long createBy;
    private Date createTime;
    private Long id;
    private Long rootId;
    private Long toCommentId;
    private Long toCommentUserId;
    private String toCommentUserName;
    private String username;
    private String avatar;
}
