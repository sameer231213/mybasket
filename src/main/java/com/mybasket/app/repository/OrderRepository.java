package com.mybasket.app.repository;

import com.mybasket.app.entity.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface OrderRepository extends JpaRepository<Order,Long> {
}
