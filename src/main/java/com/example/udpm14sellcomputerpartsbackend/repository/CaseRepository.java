package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    List<CaseEntity> findByProductId(Long id);

    // list product case with id category
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,cases.id,cases.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN CaseEntity cases ON cases.productId = pro.id " +
            "WHERE pro.categoryId = :caseId")
    Page<ProductCaseDto> listProductCase(Long caseId, Pageable pageable);

    // get one product with id product
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto(" +
            "pro.id,pro.name,pro.quantity,pro.price,pro.priceNew,pro.discount,pro.description," +
            "img.link,cases.id,cases.size,pro.categoryId) " +
            "FROM ImageEntity img " +
            "INNER JOIN ProductEntity pro ON img.product_id = pro.id " +
            "INNER JOIN CaseEntity cases ON cases.productId = pro.id " +
            "WHERE pro.id = :idPro " +
            "GROUP BY pro.id")
    List<ProductCaseDto> getOneProductCase(Long idPro);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto(" +
            "ca.id, ca.size, ca.productId, pro.name) " +
            "FROM CaseEntity ca " +
            "INNER JOIN ProductEntity pro ON ca.productId = pro.id " +
            "order by ca.id DESC ")
    List<CaseDto> getAll();


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.CateProductPcDto( pro.id, pro.name ) " +
            "from GroupComponentEntity group " +
            "inner join CategoryEntity cate on group.id = cate.groupId " +
            "inner join ProductEntity pro on pro.categoryId = cate.id " +
            "where group.id = 9")
    List<CateProductPcDto> listCateProductCaseDto();

}
