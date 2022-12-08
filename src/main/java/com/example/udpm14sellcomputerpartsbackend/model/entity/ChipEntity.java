package com.example.udpm14sellcomputerpartsbackend.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "chips")
@Data
public class ChipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "socket")
    private String socket;

    @JsonProperty("product_id")
    private Long productId;
}
