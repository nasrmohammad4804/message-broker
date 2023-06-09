package com.nasr.rabbitmqtutorial.controller;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.model.NotificationRequest;
import com.nasr.rabbitmqtutorial.publisher.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.nasr.rabbitmqtutorial.enumeration.NotificationType.EMAIL;

@RestController

public class EmailController {

    public static final String SEND_EMAIL_ROUTING_KEY = "send.email";
    @Autowired
    private NotificationPublisher notificationPublisher;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody NotificationRequest request) {

        Notification notification = Notification.builder()
                .content(request.getContent())
                .to(request.getTo()).type(EMAIL)
                .build();

        notificationPublisher.publish(notification, SEND_EMAIL_ROUTING_KEY);

        return ResponseEntity.ok("email successfully sent");
    }
}
