package com.orderservice.ddd.application.event;

import java.util.function.Consumer;

import com.orderservice.ddd.domain.event.DomainEvent;

public interface EventBus {

    <T extends DomainEvent> void subscribe(Class<T> eventType, Consumer<T> handler);

    void publish(DomainEvent event);
}
