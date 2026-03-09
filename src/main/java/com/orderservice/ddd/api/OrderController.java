package com.orderservice.ddd.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.ddd.api.dto.OrderResponseDTO;
import com.orderservice.ddd.api.dto.OrderResponseDTO.CreateOrderRequest;
import com.orderservice.ddd.api.dto.OrderResponseDTO.CreateOrderResponse;
import com.orderservice.ddd.api.dto.OrderResponseDTO.OrderResponse;
import com.orderservice.ddd.application.service.OrderService;
import com.orderservice.ddd.domain.model.Order;
import com.orderservice.ddd.domain.valueobject.Money;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CreateOrderResponse create(@RequestBody CreateOrderRequest request) {
        Money money = Money.of(request.amount(), request.currency());
        Order order = orderService.createOrder(UUID.randomUUID(), money);
        return new OrderResponseDTO.CreateOrderResponse(
                order.id(),
                order.total().toString(),
                order.paidAmount().toString(),
                order.status().name()
        );
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable("id") UUID id) {

        Order order = orderService.findById(id);

        return new OrderResponseDTO.OrderResponse(
                order.id(),
                order.total().toString(),
                order.paidAmount().toString(),
                order.status().name()
        );
    }

}
