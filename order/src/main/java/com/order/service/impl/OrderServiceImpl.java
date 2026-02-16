package com.order.service.impl;

import com.order.entity.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        double total = order.getPrice() * order.getQuantity();
        order.setTotal_price(total);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order existing = getOrderById(id);

        existing.setProductName(order.getProductName());
        existing.setQuantity(order.getQuantity());
        existing.setPrice(order.getPrice());
        existing.setTotal_price(order.getPrice() * order.getQuantity());

        return orderRepository.save(existing);
    }

    @Override
    public void deleteOrder(Long id) {
        Order existing = getOrderById(id);
        orderRepository.delete(existing);
    }
}
