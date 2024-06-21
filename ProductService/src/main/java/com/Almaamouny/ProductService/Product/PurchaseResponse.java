package com.Almaamouny.ProductService.Product;

public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        Integer price,
        Integer quantity
) {
}