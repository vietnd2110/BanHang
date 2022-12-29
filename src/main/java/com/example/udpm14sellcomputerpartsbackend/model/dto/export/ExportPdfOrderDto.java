package com.example.udpm14sellcomputerpartsbackend.model.dto.export;

import lombok.Data;

@Data
public class ExportPdfOrderDto {
    private String code;
    private String name;
    private Integer quantity;
    private Long price;
    private Long total;
    private String description;
}
