package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "psu")
@Data
public class PsuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wattage")
    private Float wattage;

    @Column(name = "size")
    private String size;

    @JsonProperty("product_id")
    private Long productId;

}
