package com.peelsannaw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailVo {
    private Integer articleId;
    private List<CommentDetailVo> children;
    private String content;
    private Integer creatBy;
    private LocalDateTime createTime;
    private Integer id;
    private Integer rootId;
    private Integer toCommentId;
    private Integer toCommentUserId;
    private String username;
}
