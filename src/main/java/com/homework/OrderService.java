package com.homework;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            orderRepository.saveOrder(order);
            return "Order processed successfully";
        } catch (Exception e) {
            return "Order processing failed";
        }

    }

    public double calculateTotal(int id) {
         Optional <Order> optOrder = orderRepository.getOrderById(id);
        if (!optOrder.isPresent()) {
            throw new NoSuchElementException();
        }
        Order order = optOrder.get();
        return order.getTotalPrice();
    }
}
