package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

public interface OrderService
{
    Order getById(long orderid);

    Order save(Order order);
}
