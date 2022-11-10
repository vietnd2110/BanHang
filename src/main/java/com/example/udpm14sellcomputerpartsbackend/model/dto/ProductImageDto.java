package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SqlResultSetMapping(
        name = "ProductImageDto",
        classes =
        @ConstructorResult(
                targetClass = ProductImageDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "createDate", type = LocalDateTime.class),
                        @ColumnResult(name = "updateDate", type = LocalDateTime.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "status", type = StatusEnum.class),
                        @ColumnResult(name = "imageLink", type = String.class),
                        @ColumnResult(name = "imageName", type = String.class),
                        @ColumnResult(name = "categoryId", type = Long.class),
                }))
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDto {

    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String description;
    private StatusEnum status;
    private String imageLink;
    private String imageName;
    private Long categoryId;


}
