package com.orderservice.ddd.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.ddd.infrastructure.entity.OrderEntity;

public interface OrderJPARepository
        extends JpaRepository<OrderEntity, UUID> {

}
