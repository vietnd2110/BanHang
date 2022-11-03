package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDto {
    private Long id;

    private BigDecimal price;

    private Integer quantity;

    private Long productId;

    private Long orderId;

    private Long userId;
}
