package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Payment;
import com.lambdaschool.sethsorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService
{
    @Autowired
    PaymentRepository paymentrepos;

    @Transactional
    @Override
    public Payment save(Payment payment)
    {
        return paymentrepos.save(payment);
    }

    @Override
    public void deleteAllPayments()
    {
        paymentrepos.deleteAll();
    }
}
