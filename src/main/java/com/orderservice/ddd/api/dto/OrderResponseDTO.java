package com.orderservice.ddd.api.dto;

import java.util.UUID;

public class OrderResponseDTO {

    public record OrderResponse(
            UUID id,
            String total,
            String paidAmount,
            String status
            ) {

    }

    public record CreateOrderRequest(
            String amount,
            String currency
            ) {

    }

    public record CreateOrderResponse(
            UUID orderId,
            String total,
            String paidAmount,
            String status
            ) {

    }

    public record PayOrderRequest(
            UUID id,
            String amount,
            String currency
            ) {

    }

    public record PayOrderResponse(
            UUID orderId,
            String total,
            String paidAmount,
            String status
            ) {

    }

}
