package com.orderservice.ddd.application.service;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.model.OrderStatus;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.domain.valueobject.Money;
import com.orderservice.ddd.infrastructure.database.DataBaseinterface;
import com.orderservice.ddd.infrastructure.database.InMemoryDatabase;
import com.orderservice.ddd.infrastructure.event.SimpleEventBus;
import com.orderservice.ddd.infrastructure.repository.InMemoryOrderRepository;

class OrderPaymentServiceTest {

    @Test
    void shouldMarkOrderAsPaidWhenTotalAmountReached() {
        DataBaseinterface<UUID, Order> database = new InMemoryDatabase<>();
        OrderRepository repository = new InMemoryOrderRepository(database);
        EventBus eventBus = new SimpleEventBus();

        OrderService orderService = new OrderService(repository, eventBus);
        PaymentService paymentService = new OrderPaymentService(repository, eventBus);

        UUID orderId = UUID.randomUUID();
        orderService.createOrder(orderId, Money.of("50.00", "USD"));
        paymentService.payOrder(orderId, Money.of("50.00", "USD"));

        Order order = repository.findById(orderId).orElseThrow();
        assertEquals(OrderStatus.PAID, order.status());
    }
}
