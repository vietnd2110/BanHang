package com.example.udpm14sellcomputerpartsbackend.model.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductChipDto {

    private Long productId;
    private String code;
    private String name;
    private Integer quantity;
    private long price;
    private long priceNew;
    private int discount;
    private String description;
    private String imageLink;
    private Long chipId;
    private String socket;
    private Long categoryId;


}
