package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Payment;

public interface PaymentService
{
    Payment save(Payment payment);

    void deleteAllPayments();
}
