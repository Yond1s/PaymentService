package com.orderservice.ddd.infrastructure.event;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.domain.event.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public final class SimpleEventBus implements EventBus {
    private final Map<Class<?>, List<Consumer<DomainEvent>>> handlers = new ConcurrentHashMap<>();

    @Override
    public <T extends DomainEvent> void subscribe(Class<T> eventType, Consumer<T> handler) {
        handlers.computeIfAbsent(eventType, ignored -> new CopyOnWriteArrayList<>())
                .add(event -> handler.accept(eventType.cast(event)));
    }

    @Override
    public void publish(DomainEvent event) {
        handlers.getOrDefault(event.getClass(), List.of())
                .forEach(handler -> handler.accept(event));
    }
}
