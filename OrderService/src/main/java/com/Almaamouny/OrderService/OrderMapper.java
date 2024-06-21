package com.Almaamouny.OrderService;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse fromOrder(Order order){

       return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getOrderDate(),
                order.getAddressId(),
                order.getTotal(),
                order.getOrderItems()
        );


    }
}
