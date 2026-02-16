package com.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private double price;
    private double total_price;
}
