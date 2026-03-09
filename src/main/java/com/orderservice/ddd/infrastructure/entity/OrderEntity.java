package com.orderservice.ddd.infrastructure.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    private BigDecimal amount;

    private BigDecimal paidAmount;

    private String currency;

    private String status;

    protected OrderEntity() {
    }

    public OrderEntity(UUID id, BigDecimal amount, BigDecimal paidAmount, String currency, String status) {
        this.id = id;
        this.amount = amount;
        this.paidAmount = paidAmount;
        this.currency = currency;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }
}
