package com.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    OrderService orderService;

    OrderRepository orderRepositoryMock;

    @BeforeEach
    void initAll() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepositoryMock);
    }

    //Успешная обработка заказа
    @Test
    void processOrderWithSuccess() {

        Order order = new Order(1, "test", 120, 3.2);

        Mockito.when(orderRepositoryMock.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);
        Mockito.verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    // Неудачная обработка заказа
    @Test
    void processOrderWithFail() {

        Order order = new Order(1, "test", 120, 3.2);

        Mockito.when(orderRepositoryMock.saveOrder(order)).thenThrow(new NullPointerException(""));

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);
        Mockito.verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    //Успешное вычисление стоимости
    @Test
    void calculateTotalWithSuccess() {

        Order order = new Order(1, "test", 3, 100);

        Mockito.when(orderRepositoryMock.getOrderById(1)).thenReturn(order);

        double total = orderService.calculateTotal(1);

        assertEquals(300.00, total);
        Mockito.verify(orderRepositoryMock, times(1)).getOrderById(1);
    }

    //заказ не найден
    @Test
    void calculateTotalWithNotFoundOrder() {

        Order order = new Order(1, "test", 3, 100);

        Mockito.when(orderRepositoryMock.getOrderById(1)).thenReturn(null);

        Assertions.assertThrows(NoSuchElementException.class, () -> orderService.calculateTotal(1));
        Mockito.verify(orderRepositoryMock, times(1)).getOrderById(1);
    }

    //Корректное вычисление с нулевым количеством или ценой
    @Test
    void calculateTotalWithZeroQuantityAndPrice() {

        Order order = new Order(1, "test", 0, 0);

        Mockito.when(orderRepositoryMock.getOrderById(1)).thenReturn(order);

        double total = orderService.calculateTotal(1);

        assertEquals(0.00, total);
        Mockito.verify(orderRepositoryMock, times(1)).getOrderById(1);

    }
}
