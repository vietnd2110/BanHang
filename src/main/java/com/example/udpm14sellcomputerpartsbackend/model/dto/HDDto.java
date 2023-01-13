package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HDDto {

    private Long id;

    @NotBlank(message = "Type is not null")
    private String type;

    @NotBlank(message = "PCle is not null")
    private String pcle;

    @NotNull(message = "productID is not null")
    private Long productId;

    private String productName;
}
