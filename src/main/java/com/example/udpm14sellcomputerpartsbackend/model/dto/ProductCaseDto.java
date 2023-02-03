package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCaseDto {

    private Long productId;
    private String name;
    private Integer quantity;
    private long price;
    private long priceNew;
    private int discount;
    private String description;
    private String imageLink;
    private Long caseId;
    private String size;
    private Long categoryId;


}
