package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Order;

import java.util.Set;

public interface OrderService
{
    Order getById(long orderid);

    Set<Order> getAdvances();

    Order save(Order order);

    void deleteAllOrders();

    void deleteOrder(long ordid);
}
