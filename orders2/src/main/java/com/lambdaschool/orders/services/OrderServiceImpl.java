package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrderRepository orderrepos;

    @Override
    public Order getById(long orderid) throws EntityNotFoundException
    {
        return orderrepos.findById(orderid).orElseThrow(() -> new EntityNotFoundException("Order " + orderid + " not found!"));
    }

    @Transactional
    @Override
    public Order save(Order order)
    {
        return orderrepos.save(order);
    }
}
