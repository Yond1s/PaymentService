package com.orderservice.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.application.service.OrderPaymentService;
import com.orderservice.ddd.application.service.OrderService;
import com.orderservice.ddd.application.service.PaymentService;
import com.orderservice.ddd.domain.event.OrderCreatedEvent;
import com.orderservice.ddd.domain.event.OrderPaidEvent;
import com.orderservice.ddd.domain.repository.OrderRepository;
import com.orderservice.ddd.infrastructure.event.SimpleEventBus;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public EventBus eventBus() {
        SimpleEventBus eventBus = new SimpleEventBus();
        eventBus.subscribe(OrderCreatedEvent.class, event
                -> System.out.println("[EVENT] Order created: " + event.orderId() + ", total=" + event.orderTotal()));
        eventBus.subscribe(OrderPaidEvent.class, event
                -> System.out.println("[EVENT] Order paid: " + event.orderId() + ", paid=" + event.paidAmount()));
        return eventBus;
    }

    public OrderService orderService(OrderRepository orderRepository, EventBus eventBus) {
        return new OrderService(orderRepository, eventBus);
    }

    public PaymentService paymentService(OrderRepository orderRepository, EventBus eventBus) {
        return new OrderPaymentService(orderRepository, eventBus);
    }
}
