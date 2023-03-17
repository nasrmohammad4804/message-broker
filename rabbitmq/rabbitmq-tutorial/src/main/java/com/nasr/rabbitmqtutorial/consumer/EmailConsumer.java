package com.nasr.rabbitmqtutorial.consumer;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.RABBITMQ_RECEIVE_EMAIL_QUEUE;

@Component
@Slf4j
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RABBITMQ_RECEIVE_EMAIL_QUEUE,containerFactory = "registrationListenerContainerFactory")
    public void consume(Notification notification) throws Exception {
        log.info("sending email request received with info : [{}] !!",notification);
        emailService.send(notification);
    }
}
