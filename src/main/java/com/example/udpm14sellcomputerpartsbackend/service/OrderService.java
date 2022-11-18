package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;

import javax.mail.MessagingException;
import java.util.List;

public interface OrderService {
    List<OrderEntity> getAll();

    OrderEntity orderConfirmed(Long orderId);

    OrderEntity beingShipped(Long orderId);

    OrderEntity cancelled(Long orderId,String reason);

    OrderEntity checkoutOrder(OrderEntity order) throws MessagingException;


    List<OrderEntity> listStatus(OrderStatusEnum status);

    OrderStatusEnum[] status();
}
