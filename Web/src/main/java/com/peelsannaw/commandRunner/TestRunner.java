package com.peelsannaw.commandRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 在spring容器初始化完成之后调用的方法 要求是要实现 CommandLineRunner接口
 */
@Component
public class TestRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {



    }
}
