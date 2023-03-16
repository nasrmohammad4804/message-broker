package com.nasr.rabbitmqtutorial.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.*;
import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public Declarables declarables() {
        return new Declarables(
                new FanoutExchange(RABBITMQ_EXCHANGE_SEND_EMAIL, true, false),
                new Queue(RABBITMQ_QUEUE_RECEIVE_EMAIL, true),
                new Binding(RABBITMQ_QUEUE_RECEIVE_EMAIL, QUEUE, RABBITMQ_EXCHANGE_SEND_EMAIL, RABBITMQ_ROUTE_KEY_EMAIL_SENDER, null)
        );
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(converter());
        return template;

    }
}
