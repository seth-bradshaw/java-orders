package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Customer;
import com.lambdaschool.sethsorders.views.OrderCount;

import java.util.List;


public interface CustomerService
{
    List<Customer> getAllCustomersOrders();

    Customer getCustomerById(long custid);

    List<Customer> getAllCustomersBySubname(String name);

    List<OrderCount> getCustomerOrderCount();

    Customer save(Customer customer);

    void delete(long custcode);

    Customer update(long custcode, Customer customer);

    void deleteAllCustomers();
}
