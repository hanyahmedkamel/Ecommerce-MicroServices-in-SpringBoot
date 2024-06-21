package com.Almaamouny.NotificationService;

import java.util.List;

public record OrderSuccessful(

        String customerEmail,
        Integer orderId,
        List<OrderItemResponse> orderItems

) {
}
