package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.CustomerRepository;
import com.lambdaschool.orders.views.OrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    CustomerRepository custrepos;

    @Override
    public List<Customer> getAllCustomersOrders()
    {
        List<Customer> myList = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(myList::add);

        return myList;
    }

    @Override
    public Customer getCustomerById(long custid) throws EntityNotFoundException
    {
        return custrepos.findById(custid).orElseThrow(() -> new EntityNotFoundException("Customer " + custid + " not found!"));
    }

    @Override
    public List<Customer> getAllCustomersBySubname(String name)
    {
        List<Customer> myList = custrepos.findByCustnameContainingIgnoringCase(name);
        return myList;
    }

    @Override
    public List<OrderCount> getCustomerOrderCount()
    {
        List<OrderCount> myList = custrepos.findMenuCounts();
        return myList;
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        return custrepos.save(customer);
    }
}
