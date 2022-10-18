package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity,Long> {

    Optional<FavoriteEntity> findByAccountId(Long accountId);

    Optional<FavoriteEntity> findByProductId(Long productId);

}
