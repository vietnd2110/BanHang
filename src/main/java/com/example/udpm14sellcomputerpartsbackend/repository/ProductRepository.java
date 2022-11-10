package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // filter theo gia tung khoang BETWEEN .. AND ...
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE product.price BETWEEN :start AND :end ")
    public Page<ProductImageDto> listFilterProduct(BigDecimal start, BigDecimal end, Pageable page);


    // filter theo gia giảm dần
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "ORDER BY product.price DESC ")
    public Page<ProductImageDto> listFilterProductPriceDesc(Pageable page);

    // filter theo gia giảm dần
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "ORDER BY product.price ASC ")
    public Page<ProductImageDto> listFilterProductPriceASC(Pageable page);


    // lấy tất cả danh sách product và ảnh
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id ")
    public Page<ProductImageDto> listProduct(Pageable page);

    //lấy tất cả danh sách product và ảnh theo id của product
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE product.id = :id")
    public Page<ProductImageDto> listProductId(Long id, Pageable page);


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id ")
    public Page<ProductImageDto> listProductAndPage(Pageable pageable);

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
            "image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id where CONCAT(product.name, ' ' , product.price, ' ') like %?1%")
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
            "image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id where product.categoryId=:id")
    public Page<ProductImageDto> findByCategory(Long id, Pageable pageable);

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
            "image.name," +
            "product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id where product.brandId=:id")
    public Page<ProductImageDto> findByBrand(Long id, Pageable pageable);

    List<ProductEntity> findAllById(Long id);


}
