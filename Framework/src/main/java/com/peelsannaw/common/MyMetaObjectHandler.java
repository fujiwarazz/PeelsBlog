package com.peelsannaw.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.peelsannaw.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义 元数据对象处理器 mp
 * @author peelsannaw
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * ThreadLocal为每一个线程提供单独一份存储空间，具有线程隔离的作用，只有在线程内才能获取到对应的值，线程外的不可访问
     * 一次HTTP请求会产生一个线程
     * 插入自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId=null;
        try{
            userId = SecurityUtils.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            userId = -1L;
        }
        log.info("公共字段自动填充:insert");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createBy", userId);
        metaObject.setValue("updateBy", userId);
    }

    /**
     * 更新数据自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId=null;
        try{
            userId = SecurityUtils.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            userId = -1L;
        }
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateBy", userId);
    }
}
