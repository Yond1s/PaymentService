package com.orderservice.ddd.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.domain.valueobject.Money;

import jakarta.transaction.Transactional;

@Service
public class OrderPaymentService implements PaymentService {

    private final OrderRepository orderRepository;
    private final EventBus eventBus;

    public OrderPaymentService(OrderRepository orderRepository, EventBus eventBus) {
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    @Transactional
    public void payOrder(UUID orderId, Money amount) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        order.pay(amount, Instant.now());
        orderRepository.save(order);
        order.pullDomainEvents().forEach(eventBus::publish);
    }
}
