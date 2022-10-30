package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PsuDto {

    private Long id;

    @NotBlank(message = "Wattage is not null")
    private String wattage;

    @NotBlank(message = "size is not null")
    private String size;

    @NotNull(message = "productID is not null")
    private Long productId;
}
