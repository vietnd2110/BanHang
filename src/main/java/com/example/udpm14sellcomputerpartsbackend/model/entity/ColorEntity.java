package com.example.udpm14sellcomputerpartsbackend.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "colors")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
