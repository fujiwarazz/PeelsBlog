package com.peelsannaw;

import com.peelsannaw.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author peelsannaw
 */
@Slf4j
@SpringBootApplication
//定时任务
//指定运行什么代码 指定什么时候运行
@EnableScheduling
@EnableSwagger2
@EnableAsync
public class BlogPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogPlusApplication.class,args);
        log.info("run success");
    }
}
