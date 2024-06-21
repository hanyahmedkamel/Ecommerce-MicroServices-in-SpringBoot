package com.Almaamouny.OrderService;

import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public record OrderResponse(
         Integer id,
         Integer customerId,
         Date orderDate,
         Integer addressId,
         Integer total,
         List<OrderItem> orderItems
) {
}
