package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBanChayDto {

    private Long id;
    private String name;
    private String code;
    private Long quantity;
    private Long total;
}
