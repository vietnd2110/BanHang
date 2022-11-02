package com.example.udpm14sellcomputerpartsbackend.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @JsonProperty("product_id")
    private Long product_id;

}
