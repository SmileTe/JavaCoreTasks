package com.homework;

import java.util.NoSuchElementException;

public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            int id = orderRepository.saveOrder(order);
            return "Order processed successfully";
        } catch (Exception e) {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new NoSuchElementException();
        }
        return order.getTotalPrice();
    }
}
