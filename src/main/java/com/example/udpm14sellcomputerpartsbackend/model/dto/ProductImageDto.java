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

}
