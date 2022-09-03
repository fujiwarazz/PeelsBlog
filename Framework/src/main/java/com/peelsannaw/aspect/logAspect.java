package com.peelsannaw.aspect;

import com.alibaba.fastjson.JSON;
import com.peelsannaw.annotation.logEnhance;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import sun.reflect.generics.tree.MethodTypeSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Aspect
@Component
@Slf4j
public class logAspect {


    //pointCut 指定切入点 被切入的点叫做jointPoint jointPoint可以获取这个切入点和相关信息 如同名字，方法等
    @Pointcut("@annotation(com.peelsannaw.annotation.logEnhance)")
    public void pt(){
    }

    /**
     * 增强
     * @param ProceedingJoinPoint 为被增强方法的封装对象
     * ProceedingJointPoint 为JointPoint的继承类 它可以使用Around 进行环绕
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint point){

        Object proceed = null;
        try {
            beforeHandler(point);
            proceed = point.proceed();
            afterHandler(proceed);
        }catch (Throwable e){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return proceed;
    }

    public void beforeHandler(ProceedingJoinPoint point){

        //RequestContextHolder线程池:
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法的名字
        logEnhance logEnhance = getLogEnhance(point);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURI());
        // 打印描述信息
        log.info("BusinessName   : {}",logEnhance.BusinessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",point.getSignature().getDeclaringType(),
                                          point.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(point.getArgs()));

    }

    private logEnhance getLogEnhance(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        return signature.getMethod().getAnnotation(logEnhance.class);
    }

    public void afterHandler(Object proceed){

        log.info("Response       : {}",JSON.toJSONString(proceed));
    }
}
