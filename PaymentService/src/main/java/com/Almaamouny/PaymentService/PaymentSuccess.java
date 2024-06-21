package com.Almaamouny.PaymentService;

public record PaymentSuccess(
        Integer customerId,
        String email,
        Integer orderId,
        Integer amount,
        PaymentMethod paymentMethod
) {
}
