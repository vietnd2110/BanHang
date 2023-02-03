package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FavoriteDto {

    private Long id;
    private String name;
    private long price;
    private long priceNew;
    private int discount;
    private Integer quantity;
    private String description;
    private StatusEnum status;
    private Long slyt;
    private String link;
    private Long productId;
    private Long categoryId;

}

