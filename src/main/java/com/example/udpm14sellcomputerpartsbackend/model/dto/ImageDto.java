package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ImageDto {

    private Long id;

    @NotBlank(message = "Image name is not empty")
    @Size(max = 50)
    private String name;

    private String link;

    private Long product_id;

}
