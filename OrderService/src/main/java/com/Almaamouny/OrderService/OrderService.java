package com.Almaamouny.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private CustomerClient customerClient;
    @Autowired PaymentClient paymentClient;
    @Autowired
    KafkaTemplate<String,OrderSuccessful> kafkaTemplate;
    @Autowired
    OrderMapper mapper;
    @Transactional
    public List<OrderItemResponse> placeAnOrder(List<OrderItemRequest> requests){

        Integer customerId=Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());

        String email=customerClient.getUser().getBody();


        List<OrderItemResponse> items=productClient.purchaseRequest(requests);

        Integer addressId=customerClient.userAddressId().getBody();


        Order order=new Order();
        order.setCustomerId(customerId);

        order.setAddressId(addressId);
        Integer total=0;

        orderRepository.save(order);


        for (var item: items) {

            total+=orderItemRepository.save( new OrderItem(new OrderItem.OrderItemId(order.getId(),item.id()),item.quantity(),order)).getQuantity()*item.price();

        }

        order.setTotal(total);

        PaymentRequest paymentRequest=new PaymentRequest(customerId,email,addressId,total,PaymentMethod.MASTER_CARD);

        orderRepository.save(order);

        paymentClient.makePayment(paymentRequest);

        kafkaTemplate.send("OrderPlaced",new OrderSuccessful(email,order.getId(),items));






        return items;


    }


    public List<Order> getAllOrdersByCustomerId(){

       return orderRepository.findByCustomerId(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));

    }

}
