package com.peelsannaw.controller;



import com.peelsannaw.annotation.logEnhance;
import com.peelsannaw.common.Res;
import com.peelsannaw.entity.Article;
import com.peelsannaw.service.ArticleService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {



    @Autowired
    private ArticleService articleService;

    @PutMapping("/updateViewCount/{id}")
    @logEnhance(BusinessName = "通过redis更新浏览次数")
    public Res<?> updateViewCount(@PathVariable Integer id){
        return articleService.updateViewCount(id);
    }

    @GetMapping("/hotArticleList")
    public Res<?> HotArticleList(){
        return articleService.getHotArticles();
    }

    @GetMapping("/articleList")
    @logEnhance(BusinessName = "获取所有文章")
    public Res<?> getArticles(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam(required = false)Long categoryId){

        return articleService.getAllArticles(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public Res<?> getArticle(@PathVariable Long id){
        return articleService.getArticleDetail(id);
    }
}
