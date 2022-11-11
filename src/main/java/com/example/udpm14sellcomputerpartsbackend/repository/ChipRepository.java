package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ChipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChipRepository extends JpaRepository<ChipEntity, Long> {
    List<ChipEntity> findByProductId(Long id);

    Page<ChipEntity> findAll(Pageable page);


    // list product chip
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link,chip.id,chip.socket,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN ChipEntity chip ON chip.productId = pro.id " +
            "WHERE pro.categoryId = :cateId")
    Page<ProductChipDto> listProductChip(Long cateId,Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link,chip.id,chip.socket,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN ChipEntity chip ON chip.productId = pro.id " +
            "WHERE pro.id = :proId " +
            "GROUP BY pro.id")
    List<ProductChipDto> getOneProductChip(Long proId);



}
