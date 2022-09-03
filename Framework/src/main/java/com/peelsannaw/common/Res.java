package com.peelsannaw.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peelsannaw.enums.AppHttpCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Res<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Res() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public Res(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Res(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Res(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Res errorResult(int code, String msg) {
        Res result = new Res();
        return result.error(code, msg);
    }

    public static Res okResult() {
        Res result = new Res();
        return result;
    }

    public static Res okResult(int code, String msg) {
        Res result = new Res();
        return result.ok(code, null, msg);
    }

    public static Res okResult(Object data) {
        Res result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static Res errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getMsg());
    }

    public static Res errorResult(AppHttpCodeEnum enums, String msg) {
        return setAppHttpCodeEnum(enums, msg);
    }

    public static Res setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    private static Res setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg) {
        return okResult(enums.getCode(), msg);
    }

    public Res<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
    public Res<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

}