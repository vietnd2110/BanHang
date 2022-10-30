package com.example.udpm14sellcomputerpartsbackend.model.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RamDto {

    private Long id;

    @NotBlank(message = "DDR is not null")
    private String ddr;

    @NotBlank(message = "Bus is not null")
    private String bus;

    @NotNull(message = "productID is not null")
    private Long productId;
}
