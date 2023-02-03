package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductRamDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.RamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {
    List<RamEntity> findByProductId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductRamDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,ram.id,ram.ddr,ram.bus,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN RamEntity ram ON ram.productId = pro.id " +
            "WHERE pro.categoryId = :caseId")
    Page<ProductRamDto> listProductRam(Long caseId, Pageable pageable);


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductRamDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,ram.id,ram.ddr,ram.bus,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN RamEntity ram ON ram.productId = pro.id " +
            "WHERE pro.id = :productId " +
            "GROUP BY pro.id")
    List<ProductRamDto> getOneProductRam(Long productId);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto( pro.id, pro.name ) " +
            "from GroupComponentEntity group " +
            "inner join CategoryEntity cate on group.id = cate.groupId " +
            "inner join ProductEntity pro on pro.categoryId = cate.id " +
            "where group.id = 5")
    List<CateProductPcDto> listCateProductRamDto();

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto(" +
            "ram.id, ram.ddr, ram.bus, ram.productId, pro.name) " +
            "FROM RamEntity ram " +
            "INNER JOIN ProductEntity pro ON ram.productId = pro.id " +
            "order by ram.id DESC ")
    List<RamDto> getAll();
}
