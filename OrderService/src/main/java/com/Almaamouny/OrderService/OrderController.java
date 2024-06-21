package com.Almaamouny.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/placeOrder")
    public List<OrderItemResponse> placeAnOrder(@RequestBody List<OrderItemRequest>requests){

        return orderService.placeAnOrder(requests);

    }

    @PostMapping("/orders")
    public List<Order> getAllOrders(){

        return orderService.getAllOrdersByCustomerId();

    }

}
