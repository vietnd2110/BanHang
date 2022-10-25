package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChipDto {

    private Long id;

    @NotBlank(message = "socket is not null")
    private String socket;

    @NotNull(message = "productID is not null")
    private Long productId;
}
