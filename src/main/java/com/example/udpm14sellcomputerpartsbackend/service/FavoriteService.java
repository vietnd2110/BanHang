package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;

public interface FavoriteService {
    FavoriteEntity favoriteProduct(Long productId);

    void deleteFavorite(Long id);
}
