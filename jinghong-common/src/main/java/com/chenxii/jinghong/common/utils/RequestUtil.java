package com.chenxii.jinghong.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    private static final String HEADER_UID = "uid";

    /**
     * 获取当前登陆用户的 uid
     *
     * @return 当前登陆用户的 uid
     */
    public static String currentUid() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader(HEADER_UID);
    }

}
