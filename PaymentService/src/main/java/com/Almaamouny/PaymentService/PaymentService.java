package com.Almaamouny.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentMapper mapper;

    @Autowired
    KafkaTemplate<String, PaymentSuccess>kafkaTemplate;
    public Integer processPayment(PaymentSuccess request){

        Payment payment=mapper.toPayment(request);

        paymentRepository.save(payment);

        kafkaTemplate.send("PaymentConfirmation",request);









        return payment.getId();

    }
}
