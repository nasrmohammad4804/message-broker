package com.nasr.rabbitmqtutorial.publisher;

import com.nasr.rabbitmqtutorial.model.EmailRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.*;

@Component
public class EmailProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*in fanout exchange we need to bind queue with exchange by route key but route key does not matter if you send anything
    * as route key while sending message to fanout exchange like bellow then exchange forward message to all queue that bind it
    * (without checking bindkey and route key is equal or not) */
    public void publish(EmailRequestDto dto){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_SEND_EMAIL, "",dto);
    }
}
