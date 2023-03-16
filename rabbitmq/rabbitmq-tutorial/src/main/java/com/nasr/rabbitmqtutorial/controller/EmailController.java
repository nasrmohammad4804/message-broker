package com.nasr.rabbitmqtutorial.controller;

import com.nasr.rabbitmqtutorial.model.EmailRequestDto;
import com.nasr.rabbitmqtutorial.publisher.EmailProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailController {

    @Autowired
    private EmailProducer emailProducer;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDto emailRequest) {

        emailProducer.publish(emailRequest);
        return ResponseEntity.ok("email successfully sent");
    }
}
