package com.Almaamouny.ProductService.Product;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Integer quantity,
        Integer price,
        Integer category

) {
}
