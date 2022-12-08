package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hd")
@Data
public class HdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String pcle;

    @JsonProperty("product_id")
    private Long productId;

}
