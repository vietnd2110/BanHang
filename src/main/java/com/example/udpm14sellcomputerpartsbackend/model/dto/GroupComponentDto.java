package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GroupComponentDto {
    private Long id;

    @NotBlank(message = "Name is not null")
    private String name;
}
