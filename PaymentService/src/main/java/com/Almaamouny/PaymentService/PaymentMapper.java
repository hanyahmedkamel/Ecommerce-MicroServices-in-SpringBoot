package com.Almaamouny.PaymentService;

import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentSuccess request){
        return Payment.builder().
                paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId()).customerId(request.customerId()).
                build();
    }
}
