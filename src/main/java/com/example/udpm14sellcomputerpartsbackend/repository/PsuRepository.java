package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPsuDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PsuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsuRepository extends JpaRepository<PsuEntity, Long> {
    List<PsuEntity> findByProductId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPsuDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.discount,pro.description," +
            "img.link,psu.id,psu.wattage,psu.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN PsuEntity psu ON psu.productId = pro.id " +
            "WHERE pro.categoryId = :cateId")
    Page<ProductPsuDto> listProductPsu(Long cateId, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPsuDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.discount,pro.description," +
            "img.link,psu.id,psu.wattage,psu.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN PsuEntity psu ON psu.productId = pro.id " +
            "WHERE pro.id = :proId " +
            "GROUP BY pro.id")
    List<ProductPsuDto> getOneProductPsu(Long proId);

}
