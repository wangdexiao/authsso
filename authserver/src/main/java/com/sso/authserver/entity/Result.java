package com.sso.authserver.entity;

import lombok.Data;

@Data
public class Result<T> {

    private T data;
    private int code;
    private String msg;


    public static <T> Result sucess(T data){
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = 1;
        result.msg = "ok";
        return result;
    }

    public static <T> Result fail(String errMsg,T data){
        Result<T> result = new Result<T>();
        result.data = data;
        result.code = -1;
        result.msg = errMsg;
        return result;
    }
}
