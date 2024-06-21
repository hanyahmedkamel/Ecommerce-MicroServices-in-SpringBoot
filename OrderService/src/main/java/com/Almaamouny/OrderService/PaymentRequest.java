package com.Almaamouny.OrderService;

public record PaymentRequest(
        Integer customerId,
        String email,
        Integer orderId,
        Integer amount,
        PaymentMethod paymentMethod
) {
}
