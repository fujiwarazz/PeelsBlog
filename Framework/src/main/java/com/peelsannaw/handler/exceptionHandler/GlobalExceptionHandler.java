package com.peelsannaw.handler.exceptionHandler;

import com.peelsannaw.common.Res;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(SystemException.class)
    public Res<?> SystemException(SystemException e){
        //打印异常信息
        log.error("出现了异常:{}",e);
        //从异常中获取信息分封装信息返回前端
        return Res.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Res<?> exceptionHandler(Exception e){
        return Res.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
