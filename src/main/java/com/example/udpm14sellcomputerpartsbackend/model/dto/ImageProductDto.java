package com.example.udpm14sellcomputerpartsbackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProductDto {

    @Id
    private Long id;
    private String name;
    private String link;
    private Long productId;
    private String productName;


}
