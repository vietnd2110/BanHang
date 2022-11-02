package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ImageDto {

    @NotBlank(message = "Image name is not empty")
    @Size(max = 50)
    private String name;
}
