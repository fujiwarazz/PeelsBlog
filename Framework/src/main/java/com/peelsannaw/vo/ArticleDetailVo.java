package com.peelsannaw.vo;

import com.peelsannaw.entity.Article;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDetailVo extends Article {
    private Long categoryId;
    private String categoryName;
}
