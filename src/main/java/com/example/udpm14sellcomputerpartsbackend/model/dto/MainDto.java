package com.example.udpm14sellcomputerpartsbackend.model.dto;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MainDto {
    private Long id;

    @NotBlank(message = "socket not null")
    private String socket;

    @Min(value = 0)
    private Integer slotRam;

    @Min(value = 0)
    private Integer ddr;

    @Min(value = 0)
    private Integer busRam;

    @Min(value = 0)
    private Integer slotM2;

    @Min(value = 0)
    private Integer slotData;

    @Min(value = 0)
    private Integer size;

    @Min(value = 0)
    private Long hdId;

    @Min(value = 0)
    private Long chipId;

    @NotNull(message = "product not null")
    @Min(value = 0)
    private Long productId;

    @Min(value = 0)
    private Long vgaId;

    @Min(value = 0)
    private Long ramId;
}
