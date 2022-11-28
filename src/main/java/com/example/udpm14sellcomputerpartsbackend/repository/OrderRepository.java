package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByStatusEquals(OrderStatusEnum status);

    List<OrderEntity> findAllByAccountId(Long accountId);

    @Query(nativeQuery = true,value = "SELECT count(*) AS 'Số lượng' FROM `orders` WHERE orders.status = ?1 and orders.account_id = ?2 ")
    long countOrderStatus(int status,Long accountId);

}
