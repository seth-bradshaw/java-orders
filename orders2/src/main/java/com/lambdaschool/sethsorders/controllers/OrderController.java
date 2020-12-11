package com.lambdaschool.sethsorders.controllers;

import com.lambdaschool.sethsorders.models.Order;
import com.lambdaschool.sethsorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable long orderid)
    {
        Order order = orderService.getById(orderid);
        return new ResponseEntity<>(order, OK);
    }

    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount()
    {
        //List or Set? I would think Set so you don't have the chance of duplicate orders, but maybe that's unnessasary
        Set<Order> orders = orderService.getAdvances();
        return new ResponseEntity<>(orders, OK);
    }

    @DeleteMapping(value = "/order/{ordid}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordid)
    {
        orderService.deleteOrder(ordid);

        return new ResponseEntity<>(OK);
    }

    @PostMapping(value = "/order", consumes = "application/json")
    public ResponseEntity<?> addNewOrder(@RequestBody Order neworder)
    {
        neworder.setOrdnum(0);
        neworder = orderService.save(neworder);

        HttpHeaders responseheaders = new HttpHeaders();
        URI orderURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ordid}").buildAndExpand(neworder.getOrdnum()).toUri();

        responseheaders.setLocation(orderURI);

        return new ResponseEntity<>(null, responseheaders, CREATED);
    }

    @PutMapping(value = "/order/{ordid}", consumes = "application/json")
    public ResponseEntity<?> editExistingOrder(@Valid @RequestBody Order neworder, @PathVariable long ordid)
    {
        neworder.setOrdnum(ordid);
        neworder = orderService.save(neworder);

        return new ResponseEntity<>(OK);
    }
}
