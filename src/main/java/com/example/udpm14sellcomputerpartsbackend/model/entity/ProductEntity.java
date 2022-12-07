package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_date",columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date",columnDefinition = "DATETIME")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Float discount;

    @Column(name = "description")
    private String description;

    private StatusEnum status;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("voucher_id")
    private Long voucherId;

    @JsonProperty("brand_id")
    private Long brandId;

    @ManyToOne
    @JoinColumn(name = "pc_id", nullable = false)
    private PCEntity pc;
}
