package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "ThongKeHoaDonDto2",
        classes =
        @ConstructorResult(
                targetClass = StatisticalDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "totalOrder", type = Long.class),
                        @ColumnResult(name = "totalMoney", type = Long.class),
                }))

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeDto {

    @Id
    private Long id;
    private Long totalOrder;
    private Long totalMoney;
}
