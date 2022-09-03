package com.peelsannaw.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peelsannaw.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo   {
    private String categoryName;
    private Long id;
    private int likeCount;
    private String summary;
    private String thumbnail;
    private String title;
    private Long viewCount;
    private LocalDateTime createTime;
    @JsonIgnore
    private Long categoryId;
}
