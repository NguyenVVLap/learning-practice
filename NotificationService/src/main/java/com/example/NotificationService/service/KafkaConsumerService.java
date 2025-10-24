package com.example.NotificationService.service;

import com.example.NotificationService.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(
            topics = "orderService",
            groupId = "groupId"
    )
    void listener(String data) {
        EmailDetails emailDetails = EmailDetails.builder()
                .msgBody("Order Created")
                .subject("ORDER SERVICE NOTIFICATION")
                .recipient("nvvlap@gmail.com")
                .build();
        emailService.sendSimpleMail(emailDetails);
        System.out.println("Listener received: " + data + " 🎉");
    }
}
