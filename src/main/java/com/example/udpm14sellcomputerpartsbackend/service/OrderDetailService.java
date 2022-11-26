package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailEntity> getAll();

    Page<OrderDetailEntity> getAllAndPage(Integer pageSize, Integer pageNumber);

    OrderDetailEntity getById(Long id);

    Page<OrderDetailEntity> getByOrder(Long id, Integer pageSize, Integer pageNumber);

    Page<OrderDetailEntity> getByUserLogin(Integer pageSize, Integer pageNumber);

    Page<OrderDetailEntity> getByUser(Long userId, Integer pageSize, Integer pageNumber);

}
