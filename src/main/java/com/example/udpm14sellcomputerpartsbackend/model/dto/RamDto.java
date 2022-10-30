package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RamDto {
    private Long id;

    @NotNull(message = "ddr is not null")
    @Min(value = 0)
    private Integer ddr;

    @Min(value = 0)
    @NotNull(message = "bus is not null")
    private Integer bus;

    @NotNull(message = "productId is not null")
    private Long productId;
}
