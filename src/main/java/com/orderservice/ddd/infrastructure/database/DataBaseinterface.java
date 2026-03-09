package com.orderservice.ddd.infrastructure.database;

import java.util.Optional;

public interface DataBaseinterface<K, V> {

    void put(K key, V value);

    Optional<V> get(K key);
}
