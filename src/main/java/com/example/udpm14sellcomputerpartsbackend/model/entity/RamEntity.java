package com.example.udpm14sellcomputerpartsbackend.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rams")
@Data
public class RamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ddr;

    private Integer bus;

    @JsonProperty("product_id")
    private Long productId;

}

