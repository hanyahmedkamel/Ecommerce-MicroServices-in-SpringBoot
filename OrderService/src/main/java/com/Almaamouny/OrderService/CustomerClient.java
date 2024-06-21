package com.Almaamouny.OrderService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USERSERVICE",path = "/User/api",configuration = FeignClientConfiguration.class)
public interface CustomerClient {

    @GetMapping("/address_id")
    public ResponseEntity<Integer> userAddressId();


    @GetMapping("/user")
    public ResponseEntity<String> getUser();


}
