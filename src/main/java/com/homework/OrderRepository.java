package com.homework;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order);
    Order getOrderById(int id);
}
