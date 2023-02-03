package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<ImageEntity,Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto(image.id,image.name,image.link,product.id,product.name) " +
            "FROM ImageEntity image INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE image.id = :id")
    ImageProductDto listImagesId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "WHERE image.product_id = :id")
    public List<ProductImageDto> listProductAndImages(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "WHERE product.code=:code " +
            "group by product.id")
    ProductImageDto ProductAndImagesByMaSp(String code);

    @Query("SELECT p FROM ImageEntity p WHERE p.product_id =?1")
    public List<ImageEntity> getImageByProduct(Long id);

}
