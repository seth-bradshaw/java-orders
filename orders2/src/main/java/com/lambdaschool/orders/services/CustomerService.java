package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.views.OrderCount;

import java.util.List;

public interface CustomerService
{
    List<Customer> getAllCustomersOrders();

    Customer getCustomerById(long custid);

    List<Customer> getAllCustomersBySubname(String name);

    List<OrderCount> getCustomerOrderCount();

    Customer save(Customer customer);
}
