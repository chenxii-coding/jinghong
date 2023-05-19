package com.chenxii.jinghong.order.service.impl;

import com.chenxii.jinghong.common.constant.MQConstant;
import com.chenxii.jinghong.order.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceImpl implements MQService {

    @Override
    public void sendMessage(String message) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer(MQConstant.MQ_GROUP_NAME);
            producer.setNamesrvAddr(MQConstant.NAMESRV_ADDR);
            producer.setVipChannelEnabled(false);
            producer.setSendMsgTimeout(10 * 1000);
            producer.setRetryTimesWhenSendFailed(3);
            producer.setDefaultTopicQueueNums(3);
            producer.start();

            SendResult sendResult = producer.send(new Message(MQConstant.TOPIC, message.getBytes()));
            log.info("【MQ】发送结果: {}", sendResult);
            if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                log.info("【MQ】消息发送成功");
            } else {
                log.error("【MQ】消息发送失败");
            }
        } catch (Exception e) {
            log.error("【MQ】消息发送失败");
            log.error("错误信息: ", e);
        }
    }
}
