package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<ImageEntity,Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto(image.id,image.name,image.link,product.id,product.name) " +
            "FROM ImageEntity image INNER JOIN ProductEntity product ON image.product_id = product.id")
    public List<ImageProductDto> listImage();

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto(image.id,image.name,image.link,product.id,product.name) " +
            "FROM ImageEntity image INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE image.id = :id")
    public List<ImageProductDto> listImagesId(Long id);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.name,product.price,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "WHERE image.product_id = :id")
    public List<ProductImageDto> listProductAndImages(Long id);

    @Query("SELECT p FROM ImageEntity p WHERE p.product_id =?1")
    public List<ImageEntity> getImageByProduct(Long id);

}
