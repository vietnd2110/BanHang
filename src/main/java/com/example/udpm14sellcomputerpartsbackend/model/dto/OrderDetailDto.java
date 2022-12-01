package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@SqlResultSetMapping(
        name = "orderDetailDto",
        classes =
        @ConstructorResult(
                targetClass = ImageProductDto.class,
                columns = {
                        @ColumnResult(name = "id", type       = Long.class),
                        @ColumnResult(name = "price", type    = Long.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "total", type    = Long.class),
                        @ColumnResult(name = "image", type    = String.class),
                        @ColumnResult(name = "product", type  = String.class),
                        @ColumnResult(name = "orderId", type  = Long.class),
                        @ColumnResult(name = "username", type = String.class),
                }))

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {

    @Id
    private Long id;

    private Long price;

    private Integer quantity;

    private Long total;

    private String image;

    private String productId;

    private Long orderId;

    private String userId;
}
