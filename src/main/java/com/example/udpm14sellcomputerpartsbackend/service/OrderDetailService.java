package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAll();
    Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber);
    OrderDetailDto getById(Long id);
    Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber);
    OrderDetailDto create(OrderDetailDto orderDetailDto);
    OrderDetailDto update(Long id, OrderDetailDto orderDetailDto);
    void delete(Long id);

}
