package com.chenxii.jinghong.inventory.config;

import com.chenxii.jinghong.inventory.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MQInit {

    @Autowired
    private MQService mqService;

    @PostConstruct
    public void initMQ() {
        log.info("【MQ】开始启动消息队列监听..");
        mqService.receiveMessage();
        log.info("【MQ】完成消息队列监听初始化");
    }

}
