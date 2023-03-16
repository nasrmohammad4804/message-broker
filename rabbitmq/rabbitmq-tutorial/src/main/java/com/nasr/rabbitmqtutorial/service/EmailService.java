package com.nasr.rabbitmqtutorial.service;

import com.nasr.rabbitmqtutorial.model.EmailRequestDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailRequestDto request) throws Exception {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(request.getTo());
            helper.setText(request.getContent());
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
