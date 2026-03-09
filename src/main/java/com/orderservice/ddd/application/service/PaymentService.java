package com.orderservice.ddd.application.service;

import java.util.UUID;

import com.orderservice.ddd.domain.valueobject.Money;

public interface PaymentService {

    void payOrder(UUID orderId, Money amount);
}
