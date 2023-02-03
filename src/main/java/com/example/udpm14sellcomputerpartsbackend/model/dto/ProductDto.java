package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.RegexContants;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "code is not null")
    private String code;

    @NotBlank(message = "product name is not null")
    @Size(max = 244)
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    private long price;
    private long priceNew;

    @NotNull(message = "quantity not is null")
    @Min(value = 0, message = "quantity min is 0")
    private Integer quantity;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int discount;
    private String description;
    private StatusEnum status;
    private Long categoryId;
    private Long brandId;
}
