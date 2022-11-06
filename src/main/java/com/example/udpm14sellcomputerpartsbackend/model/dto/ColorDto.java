package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ColorDto {
    @NotBlank(message = "ColorName not null")
    private String colorName;

    @NotNull(message = "product id not null")
    @Min(value = 1)
    private Long productId;
}
