package com.orderservice.ddd.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.domain.valueobject.Money;
import com.orderservice.ddd.exceptions.OrderNotFoundException;

@Service
public final class OrderService {

    private final OrderRepository orderRepository;
    private final EventBus eventBus;

    public OrderService(OrderRepository orderRepository, EventBus eventBus) {
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    public Order createOrder(UUID orderId, Money total) {
        Order order = Order.createNew(orderId, total);
        orderRepository.save(order);
        order.pullDomainEvents().forEach(eventBus::publish);
        return order;
    }

    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
    }
}
