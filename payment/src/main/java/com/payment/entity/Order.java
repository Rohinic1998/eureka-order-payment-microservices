package com.payment.entity;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private double total_price;
}
