package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;
    private String title;
    private Integer rating;
    private String content;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("user_id")
    private Long userId;


}
