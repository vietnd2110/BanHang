package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRamDto {

    private Long productId;
    private String name;
    private Integer quantity;
    private long price;
    private long priceNew;
    private int discount;
    private String description;
    private String imageLink;
    private Long ramId;
    private Integer ddr;
    private Integer bus;
    private Long categoryId;

}
