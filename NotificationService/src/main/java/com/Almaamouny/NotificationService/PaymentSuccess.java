package com.Almaamouny.NotificationService;

public record PaymentSuccess(
        Integer customerId,
        String email,
        Integer orderId,
        Integer amount,
        PaymentMethod paymentMethod
) {
}
