package com.nasr.rabbitmqtutorial.consumer;

import com.nasr.rabbitmqtutorial.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.*;

@Slf4j
@Component
public class EmailFailureConsumer {

    @RabbitListener(queues = RABBITMQ_RECEIVE_FAILURE_NOTIFICATION_QUEUE)
    public void consume(Notification dto){
        log.warn("notification with type : [{}] with data : [{}] dont able to be processed",dto.getType(),dto);
    }
}
