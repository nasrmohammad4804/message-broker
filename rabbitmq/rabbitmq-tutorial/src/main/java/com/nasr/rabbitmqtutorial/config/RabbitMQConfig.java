package com.nasr.rabbitmqtutorial.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

import static com.nasr.rabbitmqtutorial.constant.ConstantField.*;
import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public RetryOperationsInterceptor retryOperationsInterceptor() {
        return RetryInterceptorBuilder.stateless().maxAttempts(3)
                .backOffOptions(2000, 2, 40000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory registrationListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cachingConnectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setAdviceChain(retryOperationsInterceptor());
        return factory;
    }

    @Bean
    public Declarables declarables() {
        return new Declarables(

                new DirectExchange(RABBITMQ_SENDING_NOTIFICATION_FAILURE_EXCHANGE, true, false),
                new TopicExchange(RABBITMQ_SEND_NOTIFICATION_EXCHANGE, true, false),

                QueueBuilder.durable(RABBITMQ_RECEIVE_EMAIL_QUEUE)
                        .withArgument(RABBITMQ_X_DEAD_LETTER_EXCHANGE_ARGUMENT, RABBITMQ_SENDING_NOTIFICATION_FAILURE_EXCHANGE)
                        .withArgument(RABBITMQ_X_DEAD_LETTER_ROUTING_KEY,RABBITMQ_EMAIL_SENDER_FAILURE_ROUTE_KEY)
                        .build(),

                new Queue(RABBITMQ_RECEIVE_SMS_QUEUE,true),
                new Queue(RABBITMQ_RECEIVE_FAILURE_NOTIFICATION_QUEUE, true),

                new Binding(RABBITMQ_RECEIVE_FAILURE_NOTIFICATION_QUEUE,QUEUE, RABBITMQ_SENDING_NOTIFICATION_FAILURE_EXCHANGE,RABBITMQ_EMAIL_SENDER_FAILURE_ROUTE_KEY,null),
                new Binding(RABBITMQ_RECEIVE_EMAIL_QUEUE, QUEUE, RABBITMQ_SEND_NOTIFICATION_EXCHANGE, RABBITMQ_EMAIL_SENDER_ROUTE_KEY, null),
                new Binding(RABBITMQ_RECEIVE_SMS_QUEUE,QUEUE,RABBITMQ_SEND_NOTIFICATION_EXCHANGE,RABBITMQ_SMS_SENDER_ROUTE_KEY,null)
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
