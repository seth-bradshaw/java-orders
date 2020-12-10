package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.models.Payment;
import com.lambdaschool.orders.repositories.OrderRepository;
import com.lambdaschool.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Transactional
@Service(value = "orderServices")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrderRepository orderrepos;

    @Autowired
    PaymentRepository paymentrepos;

    @Override
    public Order getById(long orderid) throws EntityNotFoundException
    {
        return orderrepos.findById(orderid).orElseThrow(() -> new EntityNotFoundException("Order " + orderid + " not found!"));
    }

    @Override
    public Set<Order> getAdvances()
    {
        return orderrepos.getAdvanceAmount();
    }

    @Transactional
    @Override
    public Order save(Order order)
    {
        Order newOrd = new Order();

        if (order.getOrdnum() != 0)
        {
            orderrepos.findById(order.getOrdnum()).orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " not found."));
            newOrd.setOrdnum(order.getOrdnum());
        }

        newOrd.setOrdamount(order.getOrdamount());
        newOrd.setAdvanceamount(order.getAdvanceamount());
        newOrd.setOrderdescription(order.getOrderdescription());
        newOrd.setCustomer(order.getCustomer());

        newOrd.getPayments().clear();
        for (Payment p : order.getPayments())
        {
//            Payment newPay = new Payment(p.getType());
            Payment newPay = paymentrepos.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found."));
            newOrd.getPayments().add(newPay);
        }

        return orderrepos.save(newOrd);
    }

    @Override
    public void deleteAllOrders()
    {
        orderrepos.deleteAll();
    }

    @Override
    public void deleteOrder(long ordid)
    {
        orderrepos.deleteById(ordid);
    }
}
