package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mains")
@Data
public class MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socket;

    private Integer slotRam;

    private Integer ddr;

    @Column(name = "bus_ram")
    private Integer busRam;

    @Column(name = "slot_m2")
    private Integer slotM2;

    @Column(name = "slot_data")
    private Integer slotData;

    @Column(name = "size")
    private Integer size;

    @JsonProperty("hd_id")
    private Long hdId;

    @JsonProperty("chip_id")
    private Long chipId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("vga_id")
    private Long vgaId;

    @JsonProperty("ram_id")
    private Long ramId;

}
