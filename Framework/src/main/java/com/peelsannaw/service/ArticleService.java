package com.peelsannaw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Article;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

import static com.peelsannaw.common.SystemConstants.ARTICLE_STATUS_NORMAL;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2022-07-20 14:57:41
 */

public interface ArticleService extends IService<Article> {

    Res getHotArticles();

    Res getAllArticles(Integer pageNum, Integer pageSize, Long categoryId);

    Res getArticleDetail(Long aid);

    Res<?> updateViewCount(Integer id);
}

