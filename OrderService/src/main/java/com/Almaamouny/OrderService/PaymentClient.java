package com.Almaamouny.OrderService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PaymentService",path = "/Payment/api",configuration = FeignClientConfiguration.class)
public interface PaymentClient {

    @PostMapping("/makePayment")
    public Integer makePayment(PaymentRequest request);
}
