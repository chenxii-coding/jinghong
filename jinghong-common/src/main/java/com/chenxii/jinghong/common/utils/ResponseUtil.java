package com.chenxii.jinghong.common.utils;

import com.chenxii.jinghong.common.entity.Response;

public class ResponseUtil {

    public static <T> Response<T> success() {
        return new Response<T>(Result.SUCCESS);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> failed(String message) {
        return new Response<T>(Result.FAILED.getCode(), message);
    }

    public static <T> Response<T> failed() {
        return new Response<T>(Result.FAILED);
    }
}
