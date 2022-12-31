package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAll();

    Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    OrderDetailEntity getById(Long id);

    Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber);

    Page<OrderDetailDto> getByUserLogin(Integer pageSize, Integer pageNumber);

    Page<OrderDetailDto> getByUser(Long userId, Integer pageSize, Integer pageNumber);

    List<OrderDetailEntity> getAllOrderId(Long id);

    OrderDetailEntity update(Long id, OrderDetailEntity orderDetailEntity);
}
