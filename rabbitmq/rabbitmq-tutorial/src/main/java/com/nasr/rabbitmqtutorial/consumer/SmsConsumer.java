package com.nasr.rabbitmqtutorial.consumer;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.RABBITMQ_RECEIVE_SMS_QUEUE;

@Slf4j
@Component
public class SmsConsumer {

    @Autowired
    @Qualifier("smsServiceImpl")
    private NotificationService smsService;

    @RabbitListener(queues = RABBITMQ_RECEIVE_SMS_QUEUE)
    public void consume(Notification notification) throws Exception {
        log.info("sending sms request received with info : [{}] !!",notification);
        smsService.send(notification);
    }
}
