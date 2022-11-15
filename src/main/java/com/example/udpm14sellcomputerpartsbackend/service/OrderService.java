package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;

import javax.mail.MessagingException;
import java.util.List;

public interface OrderService {
    OrderEntity orderConfirmed(Long orderId);

    OrderEntity waitForPay(Long orderId);

    OrderEntity beingShipped(Long orderId);

    OrderEntity cancelled(Long orderId,String reason);

    OrderEntity checkoutOrder(OrderEntity order) throws MessagingException;

    List<OrderEntity> listStatusWaitForConfirmation();

    List<OrderEntity> listStatusOrderConfirmed();

    List<OrderEntity> listStatusWaitForPay();

    List<OrderEntity> listStatusBeingShipped();

    List<OrderEntity> listStatusDelivered();

    List<OrderEntity> listStatusCancelled();
}
