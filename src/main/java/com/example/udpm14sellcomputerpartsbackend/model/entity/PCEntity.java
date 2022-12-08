package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pc")
@Data
public class PCEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("chip_id")
    private Long chipId;

    @JsonProperty("main_id")
    private Long mainId;

    @JsonProperty("vga_id")
    private Long vgaId;

    @JsonProperty("ram_id")
    private Long ramId;

    @JsonProperty("hd_id")
    private Long hdId;

    @JsonProperty("psu_id")
    private Long psuId;

    @JsonProperty("case_id")
    private Long casesId;

    @JsonProperty("product_id")
    private Long productId;
}
