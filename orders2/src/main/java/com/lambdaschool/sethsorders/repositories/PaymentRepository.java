package com.lambdaschool.sethsorders.repositories;

import com.lambdaschool.sethsorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>
{
}
