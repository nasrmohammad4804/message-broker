package com.nasr.rabbitmqtutorial.service.impl;

import com.nasr.rabbitmqtutorial.model.Notification;
import com.nasr.rabbitmqtutorial.service.NotificationService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(Notification notification) throws Exception {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(notification.getTo());
            helper.setText(notification.getContent());
            helper.setSubject(getSubject());

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("error occurred while sending email");
        }
    }
    private String getSubject() {
        return "hi . this email for reminding ";
    }
}
