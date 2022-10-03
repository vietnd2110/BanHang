package com.example.udpm14sellcomputerpartsbackend.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@Data
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "description")
    private String description;

}
