package com.peelsannaw.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * 逆向工程
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/sg_blog?serverTimezone=GMT%2b8",
                        "root", "zzhzzhzzh1")
                .globalConfig(builder -> {
                    builder.author("peelsannaw") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\JavaProjects\\Blog\\Admin\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {

                    builder.parent("com.peelsannaw") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\JavaProjects\\Blog\\Admin\\src\\main\\resources\\Mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
//                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
//                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("sys_user_role") // 设置需要生成的表名
                            .addTablePrefix("sg_", "sys_"); // 设置过滤表前缀
                })
                .execute();
    }
}
