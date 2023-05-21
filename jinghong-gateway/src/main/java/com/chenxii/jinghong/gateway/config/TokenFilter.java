package com.chenxii.jinghong.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.gateway.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    private static final List<String> WHITE_LIST = new ArrayList<>();

    static {
        WHITE_LIST.add("/jinghong/api/goods/onSale");
        WHITE_LIST.add("/jinghong/api/gateway/userLogin");
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("【网关鉴权】请求地址：{}", url);
        if (WHITE_LIST.contains(url)) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        // token鉴权
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst("token");
        // token为空
        if (StringUtils.isBlank(token)) {
            log.error("【网关鉴权】未登录用户");
            return response.writeWith(Mono.just(failedResponse(response, "还未登陆，请先登陆")));
        }
        // 携带token
        try {
            TokenUtil.checkToken(token);
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("【网关鉴权】token超期, token: {}", token);
            return response.writeWith(Mono.just(failedResponse(response, "token已超期，请重新登陆")));
        }
    }

    private DataBuffer failedResponse(ServerHttpResponse response, String message) {
        Response<Void> response1 = ResponseUtil.failed(message);
        JSONObject res = new JSONObject();
        res.put("code", response1.getCode());
        res.put("message", response1.getMessage());
        return response.bufferFactory().wrap(res.toJSONString().getBytes(StandardCharsets.UTF_8));
    }

    public int getOrder() {
        return -100;
    }
}
