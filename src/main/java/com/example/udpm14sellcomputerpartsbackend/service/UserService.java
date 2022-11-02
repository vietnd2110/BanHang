package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long id);

    String updateImage(Long id, MultipartFile file);
}
