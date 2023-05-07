package com.chenxii.jinghong.gateway.config;

import com.chenxii.jinghong.gateway.exception.AuthException;
import com.chenxii.jinghong.gateway.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    private static final List<String> WHITE_LIST = new ArrayList<>();

    static {
        TokenFilter.WHITE_LIST.add("/jinghong/api/goods/onSale");
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String url = exchange.getRequest().getURI().getPath();
//        log.info("【网关鉴权】请求地址：{}", url);
//        if (WHITE_LIST.contains(url)) {
//            return chain.filter(exchange);
//        }
//        // token鉴权
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        String token = headers.getFirst("token");
//        // token为空
//        if (StringUtils.isBlank(token)) {
//            return Mono.error(new AuthException("请先登陆", null));
//        }
//        // 携带token
//        try {
//            TokenUtil.checkToken(token);
//            return chain.filter(exchange);
//        } catch (Exception e) {
//            log.error("【网关鉴权】token超期, token: {}", token);
//            return Mono.error(new AuthException("token已过期，请重新登陆", null));
//        }
       return  chain.filter(exchange);
    }

//    private Mono<Void> authError(ServerHttpResponse response, String message) {
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//    }

    public int getOrder() {
        return -1;
    }
}
