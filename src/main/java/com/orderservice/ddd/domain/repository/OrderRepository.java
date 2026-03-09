package com.orderservice.ddd.domain.repository;

import com.orderservice.ddd.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findById(UUID orderId);
}
