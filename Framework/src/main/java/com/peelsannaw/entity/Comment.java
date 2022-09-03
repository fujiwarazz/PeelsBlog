package com.peelsannaw.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Getter
@Setter
@TableName("sg_comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("评论类型（0代表文章评论，1代表友链评论）")
    private String type;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("根评论id")
    private Long rootId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("所回复的目标评论的userid")
    private Long toCommentUserId;

    @ApiModelProperty("回复目标评论id")
    private Long toCommentId;

    //设置自动填充
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;


}
