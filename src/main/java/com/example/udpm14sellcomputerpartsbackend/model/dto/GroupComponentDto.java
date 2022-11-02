package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GroupComponentDto {
    @NotBlank
    private String name;
}
