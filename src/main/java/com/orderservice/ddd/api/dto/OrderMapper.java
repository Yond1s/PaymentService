package com.orderservice.ddd.api.dto;

import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.model.OrderStatus;
import com.orderservice.ddd.domain.valueobject.Money;
import com.orderservice.ddd.infrastructure.entity.OrderEntity;

public class OrderMapper {

    public static Order toDomain(OrderEntity orderEntity) {

        Money amount = Money.of(orderEntity.getAmount(), orderEntity.getCurrency());
        Money paidAmount = Money.of(orderEntity.getPaidAmount(), orderEntity.getCurrency());

        Order order = Order.restore(
                orderEntity.getId(),
                amount,
                paidAmount,
                OrderStatus.valueOf(orderEntity.getStatus())
        );
        return order;
    }

    public static OrderEntity toEntity(Order order) {

        return new OrderEntity(order.id(),
                order.total().amount(),
                order.paidAmount().amount(),
                order.total().currencyCode(),
                order.status().name());

    }

}
