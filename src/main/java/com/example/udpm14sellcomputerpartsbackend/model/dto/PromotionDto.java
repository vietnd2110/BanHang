package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PromotionDto {

    @NotNull(message = "Tên rỗng")
    @NotEmpty(message = "Tên rỗng")
    @Size(min = 1, max = 300, message = "Độ dài tên từ 1 - 300 kí tự")
    private String name;

    @JsonProperty("discount_value")
    private long discountValue;

    @JsonProperty("max_value")
    private long maxValue;

    @Min(1)
    @Max(2)
    @JsonProperty("discount_type")
    private int discountType;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("expired_date")
    private Timestamp expiredDate;

    @NotNull(message = "Mã code rỗng")
    @NotEmpty(message = "Mã code rỗng")
    @Pattern(regexp="^[0-9A-Z-]+$", message = "Mã code không đúng định dạng")
    private String code;

    private boolean active;

    @JsonProperty("is_public")
    private boolean isPublic;

}
