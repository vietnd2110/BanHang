package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeCacNgayTheoThangVaNam {
    private Integer day;
    private Integer month;
    private Integer year;
    private Long totalOrder;
    private Long totalMoney;
}
