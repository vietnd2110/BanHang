package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VgaDto {
    private Long id;

    @NotBlank(message = "type is not null")
    private String type;

    @NotBlank(message = "size is not null")
    private String size;

    @NotNull(message = "productId is not null")
    private Long productId;
}
