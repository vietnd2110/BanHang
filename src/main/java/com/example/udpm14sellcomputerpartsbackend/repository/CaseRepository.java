package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    List<CaseEntity> findByProductId(Long id);

    // list product case with id category
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link,cases.id,cases.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN CaseEntity cases ON cases.productId = pro.id " +
            "WHERE pro.categoryId = :caseId")
    List<ProductCaseDto> listProductCase(Long caseId);

    // get one product with id product
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.description," +
            "img.link,cases.id,cases.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN CaseEntity cases ON cases.productId = pro.id " +
            "WHERE pro.id = :idPro")
    ProductCaseDto getOneProductCase(Long idPro);

}
