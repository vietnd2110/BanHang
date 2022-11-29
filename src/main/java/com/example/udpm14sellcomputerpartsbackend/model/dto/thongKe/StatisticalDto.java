package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.*;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "ThongKeHoaDonDto1",
        classes =
        @ConstructorResult(
                targetClass = StatisticalDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "year", type = Integer.class),
                        @ColumnResult(name = "totalOrder", type = Long.class),
                        @ColumnResult(name = "totalMoney", type = Long.class),
                }))

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalDto {

        @Id
        private Long id;
        private Integer year;
        private Long totalOrder;
        private Long totalMoney;
}
