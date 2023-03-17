package com.nasr.rabbitmqtutorial.service;

import com.nasr.rabbitmqtutorial.model.Notification;

public interface NotificationService {

    void send(Notification notification) throws Exception;

}
