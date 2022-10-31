package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity,Long> {

    Optional<FavoriteEntity> findByAccountId(Long accountId);

    Optional<FavoriteEntity> findByProductId(Long productId);

    List<FavoriteEntity> findAllByProductId(Long productId);

    List<FavoriteEntity> findAllByAccountId(Long accountId);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto(" +
            "favor.id,prod.name,prod.price,prod.quantity,prod.description,prod.status,count(favor)) FROM FavoriteEntity favor " +
            "INNER JOIN ProductEntity prod ON favor.productId = prod.id group by favor.productId")
    List<FavoriteDto> listProductFavorite();



}
