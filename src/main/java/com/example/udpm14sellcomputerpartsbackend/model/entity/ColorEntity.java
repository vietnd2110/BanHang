package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "colors")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color_name")
    private String colorName;

    @JsonProperty("product_id")
    private Long productId;


}
