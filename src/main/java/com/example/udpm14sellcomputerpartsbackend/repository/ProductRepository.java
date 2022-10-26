package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(product.id,product.name,product.price,product.quantity,product.createDate,product.updateDate,product.description,product.status,image.link,image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id ")
    public List<ProductImageDto> listProduct();

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(product.id,product.name,product.price,product.quantity,product.createDate,product.updateDate,product.description,product.status,image.link,image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE product.id = :id")
    public List<ProductImageDto> listProductId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(product.id,product.name,product.price,product.quantity,product.createDate,product.updateDate,product.description,product.status,image.link,image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id ")
    public Page<ProductImageDto> listProductAndPage(Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(product.id,product.name,product.price,product.quantity,product.createDate,product.updateDate,product.description,product.status,image.link,image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id where product.name like %:name%")
    public Page<ProductImageDto> searchByName(String name, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id," +
            "product.name," +
            "product.price," +
            "product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description," +
            "product.status," +
            "image.link," +
            "image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id where product.categoryId=:id")
    public Page<ProductImageDto> findByCategory(Long id, Pageable pageable);


    List<ProductEntity> findAllById(Long id);

}
