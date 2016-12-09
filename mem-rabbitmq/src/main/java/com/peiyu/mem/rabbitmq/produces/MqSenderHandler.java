package com.peiyu.mem.rabbitmq.produces;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/8.
 */
@Component
public class MqSenderHandler {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Logger log = Logger.getLogger(MqSenderHandler.class);

    /**
     * 发送信息
     *
     * @param messageInfo
     */
    public void sendMessage(String routingKey,Object messageInfo) {
        try {
            rabbitTemplate.convertAndSend(routingKey,messageInfo);
        } catch (Exception e) {
            log.error("发送消息失败");
        }
    }
}
