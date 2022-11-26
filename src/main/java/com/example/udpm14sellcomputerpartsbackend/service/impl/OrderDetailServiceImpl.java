package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    @Override
    public List<OrderDetailEntity> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Page<OrderDetailEntity> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<OrderDetailEntity> list = orderDetailRepository.getAllAndPage(PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public OrderDetailEntity getById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order detail id not found: " + id));
    }

    @Override
    public Page<OrderDetailEntity> getByOrder(Long id, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailEntity> list = orderDetailRepository.findAllByOrderId(id, PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailEntity> getByUserLogin(Integer pageSize, Integer pageNumber) {
        CustomerDetailService user = CurrentUserUtils.getCurrentUserUtils();
        Page<OrderDetailEntity> list = orderDetailRepository.findAllByUserId(user.getId(), PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailEntity> getByUser(Long userId, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailEntity> list = orderDetailRepository.findAllByUserId(userId, PageRequest.of(pageSize, pageNumber));
        return list;
    }

}
