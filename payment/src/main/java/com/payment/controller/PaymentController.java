package com.payment.controller;


import com.payment.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payment/api/v1")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/orderDetails/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long id) {

        //String url = "http://localhost:8080/api/v1/order/" + id;

        // Using Eureka service name "order"
        String url = "http://order/api/v1/order/" + id;

        ResponseEntity<Order> response =
                restTemplate.getForEntity(url, Order.class);

        return response;
    }
}
