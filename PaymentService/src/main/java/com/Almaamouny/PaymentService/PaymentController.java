package com.Almaamouny.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping("/makePayment")
    public Integer makePayment(@RequestBody PaymentSuccess request){
        System.out.println(request.amount());

        return service.processPayment(request);


    }
}
