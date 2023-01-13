package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorDto {
    private Long id;
    @NotBlank(message = "ColorName not null")
    private String colorName;

    @NotNull(message = "product id not null")
    @Min(value = 1)
    private Long productId;

    private String productName;
}
