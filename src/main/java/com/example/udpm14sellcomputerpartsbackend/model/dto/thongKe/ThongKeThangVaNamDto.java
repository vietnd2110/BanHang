package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "ThongKeHoaDonDto3",
        classes =
        @ConstructorResult(
                targetClass = StatisticalDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "year", type = Integer.class),
                        @ColumnResult(name = "month", type = Integer.class),
                        @ColumnResult(name = "totalOrder", type = Long.class),
                        @ColumnResult(name = "totalMoney", type = Long.class),
                }))

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeThangVaNamDto {
    @Id
    private Long id;
    private Integer year;
    private Integer month;
    private Long totalOrder;
    private Long totalMoney;
}
