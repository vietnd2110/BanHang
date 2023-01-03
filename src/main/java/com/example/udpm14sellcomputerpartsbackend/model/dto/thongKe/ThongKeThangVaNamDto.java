package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeThangVaNamDto {
    private Long id;
    private Integer year;
    private Integer month;
    private Long totalOrder;
    private Long totalMoney;
}
