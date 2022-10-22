package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
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

}
