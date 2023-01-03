package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity,Long> {

    Optional<FavoriteEntity> findByAccountId(Long accountId);

    Optional<FavoriteEntity> findByProductId(Long productId);

    List<FavoriteEntity> findAllByProductId(Long productId);

    List<FavoriteEntity> findAllByAccountId(Long accountId);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto(" +
            "favor.id,prod.name,prod.price,prod.discount,prod.quantity,prod.description," +
            "prod.status,COUNT(favor),image.link,prod.id,prod.categoryId) FROM FavoriteEntity favor " +
            "INNER JOIN ProductEntity prod ON favor.productId = prod.id " +
            "INNER JOIN ImageEntity image ON image.product_id = prod.id " +
            "WHERE favor.accountId = :accountId " +
            "GROUP BY favor.productId")
    List<FavoriteDto> listProductFavorite(Long accountId);


}
