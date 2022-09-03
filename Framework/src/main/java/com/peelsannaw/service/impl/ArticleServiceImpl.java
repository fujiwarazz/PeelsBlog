package com.peelsannaw.service.impl;

import com.aliyuncs.ram.transform.v20150501.ListUsersForGroupResponseUnmarshaller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.peelsannaw.annotation.logEnhance;
import com.peelsannaw.common.SystemConstants;
import com.peelsannaw.entity.Category;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.exception.SystemException;
import com.peelsannaw.mapper.CategoryMapper;
import com.peelsannaw.service.ICategoryService;
import com.peelsannaw.utils.BeanCopyUtils;
import com.peelsannaw.utils.RedisCache;
import com.peelsannaw.vo.ArticleDetailVo;
import com.peelsannaw.vo.ArticleListVo;
import com.peelsannaw.vo.HotArticleVo;
import com.peelsannaw.common.Res;
import com.peelsannaw.mapper.ArticleMapper;
import com.peelsannaw.entity.Article;
import com.peelsannaw.service.ArticleService;
import com.peelsannaw.vo.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.peelsannaw.common.SystemConstants.ARTICLE_STATUS_NORMAL;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-07-20 14:57:42
 */
@Service()
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ICategoryService categoryService;



    @Resource
    private RedisCache redisCache;



    @Override
    @Transactional
    @logEnhance(BusinessName = "获取文章信息")
    public Res<?> getArticleDetail(Long aid) {
        Article article = getById(aid);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //读取redis里面的访问量，
        Integer cnt =  redisCache.getCacheMapValue("article:viewCount", aid.toString());
        articleDetailVo.setViewCount(cnt.longValue());
        articleDetailVo.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
        return Res.okResult(articleDetailVo);
    }

    @Override
    public Res<?> updateViewCount(Integer id) {
        try{
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return Res.okResult();
        }catch (SystemException e){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public Res<?> getHotArticles() {
        LambdaQueryWrapper<Article> lambdaUpdateWrapper = new LambdaQueryWrapper<>();
        lambdaUpdateWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        lambdaUpdateWrapper.orderByDesc(Article::getViewCount);
        lambdaUpdateWrapper.last("limit 10");
        List<Article>list = list(lambdaUpdateWrapper);
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(list, HotArticleVo.class);
        return Res.okResult(hotArticleVos);
    }

    @Override
    public Res<?> getAllArticles(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article>queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop);
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        Page<Article>page = new Page<>(pageNum,pageSize);
        List<Article> articles = page(page, queryWrapper).getRecords();
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        articleListVos = articleListVos.stream().peek(item->{
            Category category = categoryService.getById(item.getCategoryId());
            item.setCategoryName(category.getName());
                }
        ).collect(Collectors.toList());

        //还是 page.getTotal?
        return Res.okResult(new PageVo(articleListVos,articleListVos.size()));
    }
}

