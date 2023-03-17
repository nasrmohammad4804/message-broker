package com.nasr.rabbitmqtutorial.controller;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.model.NotificationRequest;
import com.nasr.rabbitmqtutorial.publisher.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.nasr.rabbitmqtutorial.enumeration.NotificationType.SMS;

@RestController
public class SmsController {

    public static final String SEND_SMS_ROUTING_KEY = "send.sms";
    @Autowired
    private NotificationPublisher notificationPublisher;

    @PostMapping("/send-sms")
    public ResponseEntity<?> sendSms(@RequestBody NotificationRequest request) {

        Notification notification = Notification.builder()
                .to(request.getTo())
                .content(request.getContent()).type(SMS)
                .build();
        notificationPublisher.publish(notification, SEND_SMS_ROUTING_KEY);

        return ResponseEntity.ok("sms successfully sent to phoneNumber : " + request.getTo());
    }
}
