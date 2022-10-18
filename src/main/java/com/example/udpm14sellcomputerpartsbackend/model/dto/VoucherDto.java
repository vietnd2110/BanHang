package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VoucherDto {
    private Long id;

    private String code;

    private String value;

    private String detail;

    private StatusEnum status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}