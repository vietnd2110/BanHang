package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartDto {
    private Long id;

    private BigDecimal price;

    private BigDecimal total;

    private Integer quantity;

    private String image;

    private Long productId;

    private Long userId;
}
