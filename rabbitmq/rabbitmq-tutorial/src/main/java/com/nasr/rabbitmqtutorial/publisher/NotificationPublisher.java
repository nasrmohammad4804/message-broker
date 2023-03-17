package com.nasr.rabbitmqtutorial.publisher;

import com.nasr.rabbitmqtutorial.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.*;

@Component
public class NotificationPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(Notification notification, String routingKey){
        rabbitTemplate.convertAndSend(RABBITMQ_SEND_NOTIFICATION_EXCHANGE, routingKey,notification);
    }
}
