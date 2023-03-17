package com.nasr.rabbitmqtutorial.constant;

public class ConstantField {

    // queue argument
    public static final String RABBITMQ_X_DEAD_LETTER_EXCHANGE_ARGUMENT="x-dead-letter-exchange";
    public static final String RABBITMQ_X_DEAD_LETTER_ROUTING_KEY="x-dead-letter-routing-key";
    // --------------------

    // exchanges
    public static final String RABBITMQ_SEND_NOTIFICATION_EXCHANGE = "x.notification-sender-exchange";
    public static final String RABBITMQ_SENDING_NOTIFICATION_FAILURE_EXCHANGE ="x.notification-sending-failure-exchange";

    // ---------------------

    // queues
    public static final String RABBITMQ_RECEIVE_EMAIL_QUEUE = "q.email-receiver-queue";
    public static final String RABBITMQ_RECEIVE_SMS_QUEUE="q.sms-receiver-queue";
    public static final String RABBITMQ_RECEIVE_FAILURE_NOTIFICATION_QUEUE ="q.notification-receiving-failure-queue";
    // ------------------


    // route keys
    public static final String RABBITMQ_EMAIL_SENDER_ROUTE_KEY ="send.email.#";
    public static final String RABBITMQ_SMS_SENDER_ROUTE_KEY="send.sms.#";
    public static final String RABBITMQ_EMAIL_SENDER_FAILURE_ROUTE_KEY="fall-back";
    //------------------------
}
