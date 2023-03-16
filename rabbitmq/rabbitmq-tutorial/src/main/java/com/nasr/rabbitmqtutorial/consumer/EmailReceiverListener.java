package com.nasr.rabbitmqtutorial.consumer;

import com.nasr.rabbitmqtutorial.model.EmailRequestDto;
import com.nasr.rabbitmqtutorial.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.RABBITMQ_QUEUE_RECEIVE_EMAIL;

@Component
@Slf4j
public class EmailReceiverListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RABBITMQ_QUEUE_RECEIVE_EMAIL)
    public void listen(EmailRequestDto dto) throws Exception {
        //send email with email service
        log.info("send email request received with info : [{}] !!",dto);
        emailService.sendEmail(dto);
    }
}
