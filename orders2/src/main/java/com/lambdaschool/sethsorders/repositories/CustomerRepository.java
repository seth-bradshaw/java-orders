package com.lambdaschool.sethsorders.repositories;

import com.lambdaschool.sethsorders.models.Customer;
import com.lambdaschool.sethsorders.views.OrderCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustnameContainingIgnoringCase(String name);

    @Query(value = "SELECT c.custname as name, count(o.ordnum) as countorders " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY countorders DESC", nativeQuery = true)
    List<OrderCount> findMenuCounts();
}
