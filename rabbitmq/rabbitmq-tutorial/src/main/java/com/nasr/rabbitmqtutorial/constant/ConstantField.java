package com.nasr.rabbitmqtutorial.constant;

public class ConstantField {

    // exchanges
    public static final String RABBITMQ_EXCHANGE_SEND_EMAIL = "x.email-sender-exchange";

    // ---------------------

    // queues
    public static final String RABBITMQ_QUEUE_RECEIVE_EMAIL = "q.email-receiver-queue";
    // ------------------


    // route keys
    public static final String RABBITMQ_ROUTE_KEY_EMAIL_SENDER="email";

    //------------------------
}
