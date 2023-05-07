package com.chenxii.jinghong.gateway.exception;

public class AuthException extends Exception {

    private String message;

    private Throwable cause;

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
