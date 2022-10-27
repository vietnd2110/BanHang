package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;

import java.util.List;

public interface FavoriteService {
    List<FavoriteEntity> favoriteEntityList();

    FavoriteEntity findById(Long id);

    List<FavoriteEntity> listFavoriteProductId(Long productId);

    List<FavoriteEntity> listFavoriteAccountId(Long accountId);

    FavoriteEntity favoriteProduct(Long productId);

    void deleteFavorite(Long id);
}
