package com.peelsannaw.commandRunner;

import com.peelsannaw.entity.Article;
import com.peelsannaw.mapper.ArticleMapper;
import com.peelsannaw.service.ArticleService;
import com.peelsannaw.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //读取mysql文章浏览数
        List<Article> list = articleMapper.selectList(null);
        Map<String,Integer> map = new HashMap<>();
        for(Article article : list){
            map.put(article.getId().toString(),article.getViewCount().intValue());
        }
        //存入redis id:count
        redisCache.setCacheMap("article:viewCount",map);
    }
}
