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
 * 分类表
 * </p>
 *
 * @author peelsannaw
 * @since 2022-07-20
 */
@Getter
@Setter
@TableName("sg_category")
@ApiModel(value = "Category对象", description = "分类表")
//序列化是为了在redis传递信息
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类名")
    private String name;

    @ApiModelProperty("父分类id，如果没有父分类为-1")
    private Long pid;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态0:正常,1禁用")
    private String status;

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
