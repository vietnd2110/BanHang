package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseDto {
    private Long id;

    @NotBlank(message = "size is not null")
    private String size;

    @NotNull(message = "productId is not null")
    private Long productId;

    private String productName;
}
