package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductHdDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.HdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HDRepository extends JpaRepository<HdEntity, Long> {
    List<HdEntity> findByProductId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductHdDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,hd.id,hd.type,hd.pcle,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN HdEntity hd ON hd.productId = pro.id " +
            "WHERE pro.categoryId = :cateId")
    Page<ProductHdDto> listProductHd(Long cateId, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductHdDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,hd.id,hd.type,hd.pcle,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN HdEntity hd ON hd.productId = pro.id " +
            "WHERE pro.id = :proId " +
            "GROUP BY pro.id")
    List<ProductHdDto> getOneProductHd(Long proId);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto( pro.id, pro.name ) " +
            "from GroupComponentEntity group " +
            "inner join CategoryEntity cate on group.id = cate.groupId " +
            "inner join ProductEntity pro on pro.categoryId = cate.id " +
            "where group.id = 2")
    List<CateProductPcDto> listCateProductHdDto();
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto(" +
            "hd.id, hd.type,hd.pcle, hd.productId, pro.name) " +
            "FROM HdEntity hd " +
            "INNER JOIN ProductEntity pro ON hd.productId = pro.id " +
            "order by hd.id DESC ")
    List<HDDto> getAll();

}
