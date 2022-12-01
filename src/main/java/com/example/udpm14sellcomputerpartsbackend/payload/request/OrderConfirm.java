package com.example.udpm14sellcomputerpartsbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderConfirm {

    private Long id;

    private Float shipping;

}
