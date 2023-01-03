package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "TopProduct",
        classes =
        @ConstructorResult(
                targetClass = ProductImageDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "quantity", type = Long.class),
                }))
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBanChayDto {

    @Id
    private Long id;
    private String name;
    private Long quantity;
}
