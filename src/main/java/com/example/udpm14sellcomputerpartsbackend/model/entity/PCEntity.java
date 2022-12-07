package com.example.udpm14sellcomputerpartsbackend.model.entity;

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
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<ChipEntity> chip;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<MainEntity> main;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<VgaEntity> vga;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<RamEntity> ram;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<HdEntity> hd;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<PsuEntity> psu;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<CaseEntity> cases;

    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    private List<ProductEntity> product;
}
