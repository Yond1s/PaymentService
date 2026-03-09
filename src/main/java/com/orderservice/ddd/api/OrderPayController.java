package com.orderservice.ddd.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.ddd.api.dto.OrderResponseDTO.PayOrderRequest;
import com.orderservice.ddd.application.service.OrderPaymentService;
import com.orderservice.ddd.domain.valueobject.Money;

@RestController
@RequestMapping("/order/pay")
public class OrderPayController {

    private final OrderPaymentService paymentService;

    public OrderPayController(OrderPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public void payOrder(@RequestBody PayOrderRequest request) {
        Money money = Money.of(request.amount(), request.currency());
        paymentService.payOrder(request.id(), money);
    }

}
