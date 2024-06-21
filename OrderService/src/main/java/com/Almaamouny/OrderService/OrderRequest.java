package com.Almaamouny.OrderService;

import java.util.List;

public record OrderRequest(
        Integer addressId,
        List<OrderItemRequest> Items
) {
}
