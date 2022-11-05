package com.example.udpm14sellcomputerpartsbackend.model.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RamDto {

    private Long id;

    @NotNull(message = "DDR is not null")
    private Integer ddr;

    @NotNull(message = "Bus is not null")
    private Integer bus;

    @NotNull(message = "productID is not null")
    private Long productId;
}
