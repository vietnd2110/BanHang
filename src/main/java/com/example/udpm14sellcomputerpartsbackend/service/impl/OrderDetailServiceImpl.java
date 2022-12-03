package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
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
    public List<OrderDetailDto> getAll() {
        return orderDetailRepository.getAll();
    }

    @Override
    public Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.getAllAndPage(PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public OrderDetailEntity getById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order detail id not found: " + id));
    }

    @Override
    public Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.findAllByOrderId(id, PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailDto> getByUserLogin(Integer pageSize, Integer pageNumber) {
        CustomerDetailService user = CurrentUserUtils.getCurrentUserUtils();
        Page<OrderDetailDto> list = orderDetailRepository.findAllByUserId(user.getId(), PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailDto> getByUser(Long userId, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.findAllByUserId(userId, PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public List<OrderDetailEntity> getAllOrderId(Long id){
        return orderDetailRepository.findAllByOrderId(id);
    }

}
