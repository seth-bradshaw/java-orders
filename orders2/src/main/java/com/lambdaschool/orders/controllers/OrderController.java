package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    OrderService orderServices;

    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable long orderid)
    {
        Order order = orderServices.getById(orderid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount()
    {
        //List or Set? I would think Set so you don't have the chance of duplicate orders, but maybe that's unnessasary
        Set<Order> orders = orderServices.getAdvances();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
