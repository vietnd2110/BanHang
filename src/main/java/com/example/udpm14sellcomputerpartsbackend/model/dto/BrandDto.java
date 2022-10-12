package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class BrandDto {
    @NotBlank
    private String brandName;

    private String description;
}
