package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.VgaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VgaRepository extends JpaRepository<VgaEntity, Long> {
    List<VgaEntity> findByProductId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,vga.id,vga.type,vga.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN VgaEntity vga ON vga.productId = pro.id " +
            "WHERE pro.categoryId = :cateId")
    Page<ProductVgaDto> listProductVga(Long cateId, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,vga.id,vga.type,vga.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN VgaEntity vga ON vga.productId = pro.id " +
            "WHERE pro.id = :proId " +
            "GROUP BY pro.id")
    List<ProductVgaDto> getOneProductVga(Long proId);


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto( pro.id, pro.name ) " +
            "from GroupComponentEntity group " +
            "inner join CategoryEntity cate on group.id = cate.groupId " +
            "inner join ProductEntity pro on pro.categoryId = cate.id " +
            "where group.id = 6")
    List<CateProductPcDto> listCateProductVgaDto();

}
