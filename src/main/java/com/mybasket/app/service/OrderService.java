package com.mybasket.app.service;

import com.mybasket.app.dto.OrderDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(Integer userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Integer userId);
}
