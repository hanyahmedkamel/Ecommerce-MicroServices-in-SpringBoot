package com.Almaamouny.PaymentService;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
}
