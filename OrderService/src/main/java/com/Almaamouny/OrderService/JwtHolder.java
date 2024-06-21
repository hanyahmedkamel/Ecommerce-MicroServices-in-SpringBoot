package com.Almaamouny.OrderService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class JwtHolder {
    private String jwt;
}
