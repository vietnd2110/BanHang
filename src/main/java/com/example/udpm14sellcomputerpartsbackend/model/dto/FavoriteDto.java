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

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private StatusEnum status;

    private Long slyt;


}
