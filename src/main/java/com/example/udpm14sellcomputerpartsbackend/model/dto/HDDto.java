package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HDDto {

    private Long id;

    @NotBlank(message = "Type is not null")
    private String type;

    @NotBlank(message = "PCle is not null")
    private String pcle;

    @NotNull(message = "productID is not null")
    private Long productId;
}
