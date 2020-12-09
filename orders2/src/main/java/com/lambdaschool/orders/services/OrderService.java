package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

import java.util.Set;

public interface OrderService
{
    Order getById(long orderid);

    Set<Order> getAdvances();

    Order save(Order order);
}
