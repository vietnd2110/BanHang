package com.example.udpm14sellcomputerpartsbackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "ImageProductDto",
        classes =
        @ConstructorResult(
                targetClass = ImageProductDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "link", type = String.class),
                        @ColumnResult(name = "productId", type = Long.class),
                        @ColumnResult(name = "productName", type = String.class),
                }))

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductDto {

    @Id
    private Long id;
    private String name;
    private String link;
    private Long productId;
    private String productName;


}
