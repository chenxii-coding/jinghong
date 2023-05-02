package com.chenxii.jinghong.common.utils;

import lombok.Getter;

@Getter
public enum Result {

    SUCCESS(0, "处理成功"),

    FAILED(-1, "处理失败");

    private final int code;

    private final String message;

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
