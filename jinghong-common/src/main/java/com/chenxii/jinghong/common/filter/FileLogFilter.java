package com.chenxii.jinghong.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class FileLogFilter extends Filter<ILoggingEvent> {

    private static final String FILE_LOG_PREFIX = "【商品评分】";

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String message = event.getMessage();
        if (message.contains(FILE_LOG_PREFIX)) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}
