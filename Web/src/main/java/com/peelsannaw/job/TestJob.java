package com.peelsannaw.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
    /**
     * @Scheduled表明执行方法的时间
     * 从每分钟的0s开始 每隔5s执行下面代码
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void testJob(){
//        //执行的代码
//        System.out.println("任务被执行");
//    }
}
