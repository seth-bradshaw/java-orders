package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Customer;
import com.lambdaschool.sethsorders.models.Order;
import com.lambdaschool.sethsorders.models.Payment;
import com.lambdaschool.sethsorders.repositories.CustomerRepository;
import com.lambdaschool.sethsorders.repositories.PaymentRepository;
import com.lambdaschool.sethsorders.views.OrderCount;
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

    @Autowired
    PaymentRepository payrepos;

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
        Customer newCust = new Customer();

        if (customer.getCustcode() != 0)
        {
            custrepos.findById(customer.getCustcode()).orElseThrow(
                    () -> new EntityNotFoundException("Customer " + customer.getCustcode() + " not found.")
            );

            newCust.setCustcode(customer.getCustcode());
        }

        newCust.setCustname(customer.getCustname());
        newCust.setCustcity(customer.getCustcity());
        newCust.setWorkingarea(customer.getWorkingarea());
        newCust.setCustcountry(customer.getCustcountry());
        newCust.setGrade(customer.getGrade());
        newCust.setOpeningamt(customer.getOpeningamt());
        newCust.setReceiveamt(customer.getReceiveamt());
        newCust.setPaymentamt(customer.getPaymentamt());
        newCust.setOutstandingamt(customer.getOutstandingamt());
        newCust.setPhone(customer.getPhone());
        newCust.setAgent(customer.getAgent());

        newCust.getOrders().clear();

        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(),
                    o.getOrderdescription(), newCust);

            for (Payment p : newOrder.getPayments())
            {
                Payment newPay = payrepos.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found."));

                newOrder.getPayments().add(newPay);
            }

            newCust.getOrders().add(newOrder);
        }

        return custrepos.save(newCust);
    }

    @Transactional
    @Override
    public void delete(long custcode)
    {
        if (custrepos.findById(custcode).isPresent())
        {
            custrepos.deleteById(custcode);
        }
    }

    @Transactional
    @Override
    public Customer update(long custcode, Customer customer)
    {
        Customer currentCust = custrepos.findById(custcode)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + custcode + " not found!"));
        System.out.println(currentCust.getCustcode());

        if (customer.getCustname() != null)
        {
            currentCust.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null)
        {
            currentCust.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCust.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null)
        {
            currentCust.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null)
        {
            currentCust.setGrade(customer.getGrade());
        }

        if (customer.hasopeningamt != false)
        {
            currentCust.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasreceiveamt != false)
        {
            currentCust.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.haspaymentamt != false)
        {
            currentCust.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasoutstandingamt != false)
        {
            currentCust.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null)
        {
            currentCust.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null)
        {
            currentCust.setAgent(customer.getAgent());
        }

        if (customer.getOrders().size() > 0)
        {
            currentCust.getOrders().clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(),
                        o.getOrderdescription(), currentCust);
                System.out.println(newOrder.getOrdnum());
                if (o.getPayments().size() > 0)
                {
                    for (Payment p : o.getPayments())
                    {
                        Payment newPay = payrepos.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found!"));
                        newOrder.getPayments().add(newPay);
                    }
                }
                currentCust.getOrders().add(newOrder);
            }
        }

        for (Order z :currentCust.getOrders())
        {
            System.out.println(z.getOrderdescription());
        }

        return custrepos.save(currentCust);
    }

    @Transactional
    @Override
    public void deleteAllCustomers()
    {
        custrepos.deleteAll();
    }
}