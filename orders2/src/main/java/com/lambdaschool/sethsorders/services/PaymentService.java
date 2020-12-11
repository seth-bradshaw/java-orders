package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Payment;

public interface PaymentService
{
    Payment save(Payment payment);

    void deleteAllPayments();
}
