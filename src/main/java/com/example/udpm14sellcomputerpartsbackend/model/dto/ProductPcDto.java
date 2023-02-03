package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPcDto {
    private Long productId;
    private String code;
    private String name;
    private Integer quantity;
    private long price;
    private long priceNew;
    private int discount;
    private String description;
    private String imageLink;
    private String chip;
    private String main;
    private String vga;
    private Integer ram;
    private String hd;
    private Float psu;
    private String cases;
}
