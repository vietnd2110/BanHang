package com.example.udpm14sellcomputerpartsbackend.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cases")
@Data
public class CaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    @JsonProperty("product_id")
    private Long productId;

}
