package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.CountFavorite;

import java.util.List;

public interface FavoriteService {


    List<FavoriteDto> favoriteProducts();

    List<?> listProductFavoritte();

    FavoriteEntity findById(Long id);

    List<FavoriteEntity> listFavoriteProductId(Long productId);

    List<FavoriteEntity> listFavoriteAccountId(Long accountId);

    FavoriteEntity favoriteProduct(Long productId);

    void deleteFavorite(Long id);

    // đếm số lượng yêu thích theo userId
    CountFavorite countFavorite();
}
