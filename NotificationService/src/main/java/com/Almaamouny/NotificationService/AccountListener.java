package com.Almaamouny.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountListener {

    @Autowired
    EmailService service;

    @KafkaListener(topics = "ActivateAccount", groupId = "groupId", containerFactory = "integerKafkaListenerContainerFactory")
    public void sendEmail(Integer code){
        service.sendSimpleEmail("hanykamel806@gmail.com","Activation Code","Your activation code is "+code.toString());

    }
}
