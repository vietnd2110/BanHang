package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(product.id,product.name,product.price,product.quantity,product.createDate,product.updateDate,product.description,product.status,image.link,image.name) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id ")
    public List<ProductImageDto> listProduct();

}
