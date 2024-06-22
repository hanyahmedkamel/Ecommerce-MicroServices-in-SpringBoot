package com.Almaamouny.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListener {
    @Autowired
    EmailService service;

    @KafkaListener(topics = "OrderPlaced", groupId = "groupId", containerFactory = "orderSuccessfulKafkaListenerContainerFactory")
    public void listen(OrderSuccessful orderSuccessful) {
        StringBuilder text = new StringBuilder("You have ordered ");
        List<OrderItemResponse> items = orderSuccessful.orderItems();

        for (int i = 0; i < items.size(); i++) {
            OrderItemResponse item = items.get(i);
            text.append(item.quantity().toString())
                    .append(" ")
                    .append(item.name());

            if (i == items.size() - 2) {
                text.append(", and ");
            } else if (i < items.size() - 2) {
                text.append(", ");
            }
        }

        service.sendSimpleEmail("hanykamel806@gmail.com", "Order " + orderSuccessful.orderId().toString(), text.toString());

        // Add your business logic here
    }
}
