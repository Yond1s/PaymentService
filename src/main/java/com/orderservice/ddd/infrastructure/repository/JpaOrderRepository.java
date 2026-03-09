package com.orderservice.ddd.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.orderservice.ddd.api.dto.OrderMapper;
import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.infrastructure.entity.OrderEntity;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final OrderJPARepository jpaRepository;

    public JpaOrderRepository(OrderJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository
                .findById(id)
                .map(OrderMapper::toDomain);
    }
}
