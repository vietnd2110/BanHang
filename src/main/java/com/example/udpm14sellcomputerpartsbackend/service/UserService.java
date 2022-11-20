package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long id);

    String updateImage(Long id, MultipartFile file);

    Page<UserEntity> findAllByStaff(Integer pageSize, Integer pageNumber);
    Page<UserEntity> findAllByAdmin(Integer pageSize, Integer pageNumber);
    Page<UserEntity> findAllByCustomer(Integer pageSize, Integer pageNumber);
}
