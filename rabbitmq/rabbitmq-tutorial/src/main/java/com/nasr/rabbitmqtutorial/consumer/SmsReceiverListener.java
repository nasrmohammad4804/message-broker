package com.nasr.rabbitmqtutorial.consumer;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.RABBITMQ_RECEIVE_SMS_QUEUE;

@Slf4j
@Component
public class SmsReceiverListener {

    @Autowired
    private SmsService smsService;

    @RabbitListener(queues = RABBITMQ_RECEIVE_SMS_QUEUE)
    public void consume(Notification notification) throws IOException {
        log.info("sending sms request received with info : [{}] !!",notification);
        smsService.send(notification);
    }
}
