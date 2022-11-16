package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PaymentDto {

    private Long id;
    private String name;
    private String image;
    private String description;

}
