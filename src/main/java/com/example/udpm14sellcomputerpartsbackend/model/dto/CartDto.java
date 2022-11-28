package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class CartDto {
    private Long id;

    private String name;

    private long price;

    private long total;

    private Integer quantity;

    private String image;

    @NotNull
    private Long productId;

    private Long userId;
}
