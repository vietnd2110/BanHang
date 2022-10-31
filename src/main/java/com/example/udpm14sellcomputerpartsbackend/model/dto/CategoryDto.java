package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Category name is not empty")
    @Size(min = 1,max = 50,message = "Category name length from 1 to 50")
    private String name;

    private LocalDate createDate;

    private String images;

    private LocalDate updateDate;

    private StatusEnum status;

    @JsonProperty("groupId")
    private Long groupId;
}
