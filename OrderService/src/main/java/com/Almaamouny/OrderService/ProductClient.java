package com.Almaamouny.OrderService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "PRODUCT-SERVICE",path = "/Product/api",configuration = FeignClientConfiguration.class)
public interface ProductClient {

    @PostMapping("/product")
    public List<OrderItemResponse> purchaseRequest (@RequestBody List<OrderItemRequest> requests);
}
