package com.Almaamouny.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentListener {

    @Autowired
    EmailService service;
    @KafkaListener(topics = "PaymentConfirmation", groupId = "groupId", containerFactory = "paymentSuccessKafkaListenerContainerFactory")
    public void listen(PaymentSuccess paymentSuccess) {

        service.sendSimpleEmail("hanykamel806@gmail.com","Thank you for purchase from our end  ","you have paid "+paymentSuccess.amount().toString()+"$ by "+paymentSuccess.paymentMethod());
    }
}