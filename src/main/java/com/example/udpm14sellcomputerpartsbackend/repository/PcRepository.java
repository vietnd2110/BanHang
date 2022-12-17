package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPcDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PCEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcRepository extends JpaRepository<PCEntity, Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPcDto(" +
            "pro.id,pro.code,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link, chip.socket, main.socket, vga.type, ram.ddr, hd.type, psu.wattage, cases.size) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN PCEntity   pc     ON pc.productId   = pro.id " +
            "INNER JOIN ChipEntity chip   ON pc.chipId      = chip.id " +
            "INNER JOIN MainEntity main   ON pc.mainId      = main.id " +
            "INNER JOIN VgaEntity  vga    ON pc.vgaId       = vga.id " +
            "INNER JOIN RamEntity  ram    ON pc.ramId       = ram.id " +
            "INNER JOIN HdEntity   hd     ON pc.hdId        = hd.id " +
            "INNER JOIN PsuEntity  psu    ON pc.psuId       = psu.id " +
            "INNER JOIN CaseEntity cases  ON pc.casesId     = cases.id " +
            "GROUP BY pro.id")
    Page<ProductPcDto> listProductPc(Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPcDto(" +
            "pro.id,pro.code,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link, chip.socket, main.socket, vga.type, ram.ddr, hd.type, psu.wattage, cases.size) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN PCEntity   pc     ON pc.productId   = pro.id " +
            "INNER JOIN ChipEntity chip   ON pc.chipId      = chip.id " +
            "INNER JOIN MainEntity main   ON pc.mainId      = main.id " +
            "INNER JOIN VgaEntity  vga    ON pc.vgaId       = vga.id " +
            "INNER JOIN RamEntity  ram    ON pc.ramId       = ram.id " +
            "INNER JOIN HdEntity   hd     ON pc.hdId        = hd.id " +
            "INNER JOIN PsuEntity  psu    ON pc.psuId       = psu.id " +
            "INNER JOIN CaseEntity cases  ON pc.casesId     = cases.id " +
            "WHERE pro.categoryId=:cateId " +
            "GROUP BY pro.id")
    Page<ProductPcDto> listProductPcByCategory(Long cateId, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPcDto(" +
            "pro.id,pro.code,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link, chip.socket, main.socket, vga.type, ram.ddr, hd.type, psu.wattage, cases.size) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN PCEntity   pc     ON pc.productId   = pro.id " +
            "INNER JOIN ChipEntity chip   ON pc.chipId      = chip.id " +
            "INNER JOIN MainEntity main   ON pc.mainId      = main.id " +
            "INNER JOIN VgaEntity  vga    ON pc.vgaId       = vga.id " +
            "INNER JOIN RamEntity  ram    ON pc.ramId       = ram.id " +
            "INNER JOIN HdEntity   hd     ON pc.hdId        = hd.id " +
            "INNER JOIN PsuEntity  psu    ON pc.psuId       = psu.id " +
            "INNER JOIN CaseEntity cases  ON pc.casesId     = cases.id " +
            "WHERE pro.id=:productId " +
            "GROUP BY pro.id")
    List<ProductPcDto> ProductPcByProductId(Long productId);
}
