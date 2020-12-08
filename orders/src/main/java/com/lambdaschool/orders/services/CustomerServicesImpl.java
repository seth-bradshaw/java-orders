package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomersRepository custrepos;

    @Override
    public List<Customer> findAllOrders()
    {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long custid) throws EntityNotFoundException
    {
        return custrepos.findById(custid).orElseThrow(() -> new EntityNotFoundException("Customer " + custid + " not found!"));
    }

    @Override
    public List<Customer> findCustomerByLikeName(String subname)
    {
        List<Customer> list = custrepos.findByCustnameContainingIgnoringCase(subname);
        return list;
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        return custrepos.save(customer);
    }
}
