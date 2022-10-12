package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class BrandDto {
    @NotBlank(message = "vui lòng nhập name brand")
    private String brandName;

    private String description;
}
