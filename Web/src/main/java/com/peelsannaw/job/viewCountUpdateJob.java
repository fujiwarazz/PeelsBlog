package com.peelsannaw.job;

import com.peelsannaw.entity.Article;
import com.peelsannaw.service.ArticleService;
import com.peelsannaw.utils.RedisCache;
import io.swagger.models.auth.In;
import javafx.util.Pair;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class viewCountUpdateJob {

    @Autowired
    RedisCache redisCache;

    @Autowired
    private ArticleService articleService;
    //每隔10分钟将redis数据更新到sql
    @Scheduled(cron = "0 0/5 * * * ?")
    public void viewCountUpdateJob(){
        //查询redis数据
        Map<String,Integer> map = redisCache.getCacheMap("article:viewCount");
        //更新sql
        //entrySet是key和value对应的一个set
        List<Article> collect = map.entrySet().stream().map(entry ->
                new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue())).collect(Collectors.toList());
        articleService.updateBatchById(collect);
    }
}
