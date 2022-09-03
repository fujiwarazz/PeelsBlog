package com.peelsannaw.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 需要登录的才能查询信息的注解
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface NeedLogin {

}
