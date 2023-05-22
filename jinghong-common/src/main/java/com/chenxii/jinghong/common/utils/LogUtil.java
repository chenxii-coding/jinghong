package com.chenxii.jinghong.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

    public static void trace(String var1, Object... var2) {
        log.trace(var1, var2);
    }

    public static void debug(String var1, Object... var2) {
        log.debug(var1, var2);
    }

    public static void info(String var1, Object... var2) {
        log.info(var1, var2);
    }

    public static void warn(String var1, Object... var2) {
        log.warn(var1, var2);
    }


    public static void error(String var1, Object... var2) {
        log.error(var1, var2);
    }

}
