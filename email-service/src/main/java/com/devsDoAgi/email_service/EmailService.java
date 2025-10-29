package com.devsDoAgi.email_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "topic-email-sender", groupId = "email-group", containerFactory = "emailKafkaListenerFactory")
    public void sendEmail(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom("controleequipamentos@email.com");
        message.setTo(email.destinatario());
        message.setSubject(email.assunto());
        message.setText(email.corpo());
        mailSender.send(message);

        System.out.println("Recebido email para: " + email.destinatario());
        System.out.println("Assunto: " + email.assunto());
        System.out.println("Corpo: " + email.corpo());
    }
}
