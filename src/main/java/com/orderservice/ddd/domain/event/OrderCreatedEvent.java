package com.orderservice.ddd.domain.event;

import com.orderservice.ddd.domain.valueobject.Money;

import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        Money orderTotal,
        Instant occurredAt
) implements DomainEvent {
}
