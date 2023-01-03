package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalDto {

        private Long id;
        private Integer year;
        private Long totalOrder;
        private Long totalMoney;
}
