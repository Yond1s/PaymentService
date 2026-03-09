package com.orderservice.ddd.infrastructure.database;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryDatabase<K, V> implements DataBaseinterface<K, V> {

    private final Map<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public void put(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public Optional<V> get(K key) {
        return Optional.ofNullable(storage.get(key));
    }
}
