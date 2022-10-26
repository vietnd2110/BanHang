package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDate createDate;

    private String images;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name = "status")
    private StatusEnum status;

    @JsonProperty("group_id")
    private Long groupId;


}
