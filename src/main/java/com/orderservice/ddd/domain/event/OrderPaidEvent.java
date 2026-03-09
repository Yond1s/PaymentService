package com.orderservice.ddd.domain.event;

import com.orderservice.ddd.domain.valueobject.Money;

import java.time.Instant;
import java.util.UUID;

public record OrderPaidEvent(
        UUID orderId,
        Money paidAmount,
        Money orderTotal,
        Instant occurredAt
) implements DomainEvent {
}
