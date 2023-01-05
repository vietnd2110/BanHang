package com.example.udpm14sellcomputerpartsbackend.model.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {

    private Long id;

    private Long price;

    private String name;

    private Integer quantity;

    private Long total;

    private String image;

    private Long productId;

    private Long orderId;

    private Long userId;

    private String username;
}
