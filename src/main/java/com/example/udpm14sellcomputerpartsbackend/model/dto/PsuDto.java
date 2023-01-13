package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsuDto {

    private Long id;

    @NotNull(message = "Wattage is not null")
    private Float wattage;

    @NotBlank(message = "size is not null")
    private String size;

    @NotNull(message = "productID is not null")
    private Long productId;

    private String productName;
}
