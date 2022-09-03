package com.peelsannaw;

import com.peelsannaw.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@SpringBootTest
public class Test {

    @Autowired
    RedisCache redisCache;

    @org.junit.jupiter.api.Test
    public void test(){

        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        redisCache.setCacheObject("date",simpleDateFormat.format(now));
    }

}
