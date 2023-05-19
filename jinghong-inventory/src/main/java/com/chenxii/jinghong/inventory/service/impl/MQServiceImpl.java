package com.chenxii.jinghong.inventory.service.impl;

import com.chenxii.jinghong.inventory.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import static com.chenxii.jinghong.common.constant.MQConstant.*;

@Service
@Slf4j
public class MQServiceImpl implements MQService {

    @Override
    public void receiveMessage() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setNamesrvAddr(NAMESRV_ADDR);
            consumer.setConsumerGroup(MQ_GROUP_NAME);
            consumer.setConsumeTimeout(10 * 1000);
            consumer.setMaxReconsumeTimes(1);
            consumer.setVipChannelEnabled(false);
            consumer.setPullInterval(5 * 1000);
            consumer.setMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                for (MessageExt messageExt : list) {
                    byte[] body = messageExt.getBody();
                    log.info("【MQ】接收到消息: {}", new String(body));
                }
                log.info("【MQ】ack: {}", consumeConcurrentlyContext.getAckIndex());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.subscribe(TOPIC, "*");
            consumer.start();
        } catch (Exception e) {
            log.error("【MQ】接收消息失败");
            log.error("错误信息: ", e);
        }
    }
}
