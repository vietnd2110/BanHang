package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.name," +
            "o.orderId," +
            "us.username) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id")
    Page<OrderDetailDto> getAllAndPage(Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.name," +
            "o.orderId," +
            "us.username) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id")
    List<OrderDetailDto> getAll();

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.name," +
            "o.orderId," +
            "us.username) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "WHERE o.orderId = ?1")
    Page<OrderDetailDto> findAllByOrderId(Long id, Pageable pageable);

    List<OrderDetailEntity> findAllByOrderIdAndUserId(Long id,Long userId);

    List<OrderDetailEntity> findAllByUserId(Long id);
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.name," +
            "o.orderId," +
            "us.username) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "WHERE o.userId = ?1")
    Page<OrderDetailDto> findAllByUserId(Long userId, Pageable pageable);

}
