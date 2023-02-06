package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = "ProductFavoriteDto",
        classes =
        @ConstructorResult(
                targetClass = ProductFavoriteDto.class,
                columns = {
                        @ColumnResult(name = "product_id", type = Long.class),
                        @ColumnResult(name = "slyt", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "price", type = long.class),
                        @ColumnResult(name = "price_new", type = long.class),
                        @ColumnResult(name = "discount", type = int.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "link", type = String.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFavoriteDto {

    @Id
    private Long product_id;
    private Integer slyt;
    private String name;
    private long price;
    private long price_new;
    private int discount;
    private Integer quantity;
    private Integer status;
    private String description;
    private String link;

}
