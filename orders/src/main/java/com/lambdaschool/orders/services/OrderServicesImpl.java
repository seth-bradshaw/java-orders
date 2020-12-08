package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrdersRepository ordersrepos;

    @Override
    public Order getOrderById(long orderid) throws EntityNotFoundException
    {
        return ordersrepos.findById(orderid).orElseThrow(() -> new EntityNotFoundException("Order " + orderid + " not found!"));
    }

    @Transactional
    @Override
    public Order save(Order order)
    {
        return ordersrepos.save(order);
    }
}
