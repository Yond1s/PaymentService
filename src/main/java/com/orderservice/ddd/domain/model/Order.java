package com.orderservice.ddd.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.orderservice.ddd.domain.event.DomainEvent;
import com.orderservice.ddd.domain.event.OrderCreatedEvent;
import com.orderservice.ddd.domain.event.OrderPaidEvent;
import com.orderservice.ddd.domain.valueobject.Money;

public final class Order {

    private final UUID id;
    private final Money total;
    private Money paidAmount;
    private OrderStatus status;
    private final List<DomainEvent> domainEvents;

    private Order(UUID id, Money total, Money paidAmount, OrderStatus status) {
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        if (total == null || paidAmount == null || status == null) {
            throw new IllegalArgumentException("Order fields cannot be null");
        }
        this.id = id;
        this.total = total;
        this.paidAmount = paidAmount;
        this.status = status;
        this.domainEvents = new ArrayList<>();
    }

    public static Order restore(UUID id, Money total, Money paidAmount, OrderStatus status) {
        return new Order(id, total, paidAmount, status);
    }

    public static Order createNew(UUID id, Money total) {
        Order order = new Order(id, total, Money.zero(total.currencyCode()), OrderStatus.NEW);
        order.recordEvent(new OrderCreatedEvent(order.id, order.total, Instant.now()));
        return order;
    }

    public void pay(Money amount, Instant paidAt) {
        if (amount == null || paidAt == null) {
            throw new IllegalArgumentException("Payment amount and time cannot be null");
        }
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Order is already fully paid");
        }

        Money newPaid = paidAmount.add(amount);
        if (newPaid.isGreaterThan(total)) {
            throw new IllegalArgumentException("Overpayment is not allowed");
        }

        this.paidAmount = newPaid;
        this.status = paidAmount.isGreaterThanOrEqual(total) ? OrderStatus.PAID : OrderStatus.PARTIALLY_PAID;

        if (this.status == OrderStatus.PAID) {
            recordEvent(new OrderPaidEvent(id, paidAmount, total, paidAt));
        }
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }

    public UUID id() {
        return id;
    }

    public Money total() {
        return total;
    }

    public Money paidAmount() {
        return paidAmount;
    }

    public OrderStatus status() {
        return status;
    }

    private void recordEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }
}
