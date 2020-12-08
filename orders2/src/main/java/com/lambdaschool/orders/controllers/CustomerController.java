package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import com.lambdaschool.orders.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    CustomerService custServices;

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> getAllCustomerOrders()
    {
        List<Customer> myList = custServices.getAllCustomersOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long custid)
    {
        Customer cust = custServices.getCustomerById(custid);
        return new ResponseEntity<>(cust, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> getCustomerBySubname(@PathVariable String subname)
    {
        List<Customer> myList = custServices.getAllCustomersBySubname(subname);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getCustomerOrderCount()
    {
        List<OrderCount> myList = custServices.getCustomerOrderCount();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}
