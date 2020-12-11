package com.lambdaschool.sethsorders.controllers;

import com.lambdaschool.sethsorders.models.Customer;
import com.lambdaschool.sethsorders.services.CustomerService;
import com.lambdaschool.sethsorders.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> getAllCustomerOrders()
    {
        List<Customer> myList = customerService.getAllCustomersOrders();

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long custid)
    {
        Customer cust = customerService.getCustomerById(custid);

        return new ResponseEntity<>(cust, HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> getCustomerBySubname(@PathVariable String subname)
    {
        List<Customer> myList = customerService.getAllCustomersBySubname(subname);

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getCustomerOrderCount()
    {
        List<OrderCount> myList = customerService.getCustomerOrderCount();

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addCustomerById(
            @Valid
            @RequestBody
                    Customer customer)
    {
        customer.setCustcode(0);
        customer = customerService.save(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI customerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(customer.getCustcode()).toUri();
        responseHeaders.setLocation(customerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> putCustomerById(
            @Valid
            @RequestBody
                Customer customer,
            @PathVariable long custcode)
    {
        customer.setCustcode(custcode);
        customerService.save(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> editExistingCustomerById(@PathVariable long custcode, @RequestBody Customer customer)
    {
        customerService.update(custcode, customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerService.delete(custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
