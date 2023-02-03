package com.example.udpm14sellcomputerpartsbackend.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RamDto {

    private Long id;

    @NotNull(message = "DDR is not null")
    private Integer ddr;

    @NotNull(message = "Bus is not null")
    private Integer bus;

    @NotNull(message = "product id is not null")
    private Long productId;

    private String productName;
}
