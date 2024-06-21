package com.Almaamouny.OrderService;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
public class FeignClientConfiguration {

    @Autowired
    private JwtHolder tempclass;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {


            @Override
            public void apply(RequestTemplate requestTemplate) {

                requestTemplate.header("Authorization", tempclass.getJwt());
                System.out.println("Authorization "+ tempclass.getJwt());
                System.out.println("Feign request to: " + requestTemplate.url());

            }

        };
    }
}
