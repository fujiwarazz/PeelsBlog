package com.peelsannaw.exception;

import com.peelsannaw.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
