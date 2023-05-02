package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.utils.Result;
import lombok.Data;

@Data
public class Response<T> {

    private int code;

    private String message;

    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Result result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}
