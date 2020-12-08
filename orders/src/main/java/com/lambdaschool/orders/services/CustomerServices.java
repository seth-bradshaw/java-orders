package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    List<Customer> findAllOrders();

    Customer findCustomerById(long custid);

    List<Customer> findCustomerByLikeName(String subname);

    Customer save(Customer customer);
}
