package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerServices;
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
    private CustomerServices customerServices;

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomerOrders()
    {
        List<Customer> myCustomers = customerServices.findAllOrders();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/{custid}", produces = "application/json")
    public ResponseEntity<?> listCustomerById(@PathVariable long custid)
    {
        Customer customer = customerServices.findCustomerById(custid);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByLikeName(@PathVariable String subname)
    {
        List<Customer> customerList = customerServices.findCustomerByLikeName(subname);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
}
