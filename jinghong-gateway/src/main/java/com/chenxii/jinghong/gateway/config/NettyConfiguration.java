package com.chenxii.jinghong.gateway.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * 解决网关 HTTP header is larger than 8192 bytes 报错问题
 */
@Configuration
public class NettyConfiguration implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Override
    public void customize(NettyReactiveWebServerFactory factory) {

        NettyServerCustomizer nettyServerCustomizer =
                httpServer -> httpServer.httpRequestDecoder(
                        httpRequestDecoderSpec -> httpRequestDecoderSpec.maxHeaderSize(2 * 1024 * 1024));
        factory.addServerCustomizers(nettyServerCustomizer);
    }

}
