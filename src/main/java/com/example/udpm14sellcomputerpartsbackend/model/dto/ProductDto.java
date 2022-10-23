package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.RegexContants;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "product name is not null")
    @Size(max = 50)
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "quantity not is null")
    @Min(value = 0, message = "quantity min is 0")
    private Integer quantity;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String description;
    private StatusEnum status;
    private Long categoryId;
    private Long voucherId;
}
