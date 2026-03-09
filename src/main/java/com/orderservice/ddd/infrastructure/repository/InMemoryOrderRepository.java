package com.orderservice.ddd.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.infrastructure.database.DataBaseinterface;

public final class InMemoryOrderRepository implements OrderRepository {

    private final DataBaseinterface<UUID, Order> database;

    public InMemoryOrderRepository(DataBaseinterface<UUID, Order> database) {
        this.database = database;
    }

    @Override
    public void save(Order order) {
        database.put(order.id(), order);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return database.get(orderId);
    }
}
