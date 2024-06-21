package com.Almaamouny.ProductService.Product;

public record ProductRequest(
        Integer id,
        String name,
        String description,
        Integer quantity,

        Integer price,

        Integer category


) {
}
