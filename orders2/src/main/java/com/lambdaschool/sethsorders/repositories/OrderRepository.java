package com.lambdaschool.sethsorders.repositories;

import com.lambdaschool.sethsorders.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Long>
{
    @Query(value = "SELECT * " +
            "FROM orders o LEFT JOIN customers c " +
            "ON o.custcode = c.custcode " +
            "WHERE o.advanceamount > 0 " +
            "GROUP BY o.ordnum ", nativeQuery = true)
    Set<Order> getAdvanceAmount();
}
