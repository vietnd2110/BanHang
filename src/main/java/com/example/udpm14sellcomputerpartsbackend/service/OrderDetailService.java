package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.orderDetail.TotalPriceResponse;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAll();

    Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    OrderDetailEntity getById(Long id);

    Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber);

    Page<OrderDetailDto> getByUserLogin(Integer pageSize, Integer pageNumber);

    Page<OrderDetailDto> getByUser(Long userId, Integer pageSize, Integer pageNumber);

    Collection<OrderDetailEntity> getAllOrderId(Long id);

    OrderDetailEntity update(Long id, OrderDetailDto orderDetailDto);

    OrderDetailEntity addOrderDetail(Long idOrder, Long idProduct);
    OrderDetailEntity updateQuantity(Long productId, Long orderId, Integer quantity);


    OrderDetailEntity updateQuantitys(Long productId, Long orderId, Integer quantity);

    void delete(Long id);

    void deleteAllByOrderId(Long orderId);

    TotalPriceResponse total(Long orderId);

    //void delete(Long id);
}
