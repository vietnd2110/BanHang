package com.example.udpm14sellcomputerpartsbackend.service.impl;


import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.FavoriteRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.FavoriteService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {


    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public FavoriteServiceImpl(
            FavoriteRepository favoriteRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }


    @Override
    public FavoriteEntity favoriteProduct(Long productId) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();

        UserEntity findById = userRepository.findById(uDetailService.getId()).
                orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Username not found: " + uDetailService.getId()));

        ProductEntity findByProductId = productRepository.findById(productId).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));

        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setProductId(productId);
        favoriteEntity.setAccountId(uDetailService.getId());

        return favoriteRepository.save(favoriteEntity);
    }


    @Override
    public void deleteFavorite(Long id){

        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        if(uDetailService == null){
            throw new BadRequestException("Bạn chưa đăng nhâp");
        }


        FavoriteEntity findById = favoriteRepository.findById(id).
                orElseThrow(()-> new NotFoundException(HttpStatus.NO_CONTENT.value(), "Favorite id not found: " + id));
        favoriteRepository.deleteById(findById.getId());
    }


}
