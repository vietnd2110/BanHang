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
            "product.name," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.id," +
            "o.orderId," +
            "us.id," +
            "us.fullname) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "group by o.id")
    Page<OrderDetailDto> getAllAndPage(Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "product.name," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.id," +
            "o.orderId," +
            "us.id," +
            "us.fullname) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "group by o.id")
    List<OrderDetailDto> getAll();

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "product.name," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.id," +
            "o.orderId," +
            "us.id," +
            "us.fullname) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "WHERE o.orderId = ?1 " +
            "group by o.id")
    Page<OrderDetailDto> findAllByOrderId(Long id, Pageable pageable);

    List<OrderDetailEntity> findAllByOrderIdAndUserId(Long id,Long userId);

    List<OrderDetailEntity> findAllByUserIdAndOrderId(Long userId, Long orderId);

    List<OrderDetailEntity> findAllByUserId(Long userId);


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto(" +
            "o.id," +
            "o.price," +
            "product.name," +
            "o.quantity," +
            "o.total," +
            "im.link," +
            "product.id," +
            "o.orderId," +
            "us.id," +
            "us.fullname) " +
            "FROM OrderDetailEntity o " +
            "INNER JOIN ProductEntity product ON o.productId = product.id " +
            "INNER JOIN ImageEntity im ON o.productId = im.product_id " +
            "INNER JOIN UserEntity us ON o.userId = us.id " +
            "WHERE o.userId = ?1 " +
            "group by o.id")
    Page<OrderDetailDto> findAllByUserId(Long userId, Pageable pageable);

    List<OrderDetailEntity> findAllByOrderId(Long id);

//    OrderDetailEntity findAllByUserIdAndOrderId(Long userId,Long orderId);
    OrderDetailEntity findAllByOrderIdAndProductId(Long userId,Long productId);


    @Query(value = "SELECT SUM(order_details.total) as 'Tông tiền' FROM `order_details` WHERE order_details.order_id = ?1",nativeQuery = true)
    Long sumPrice(Long idOrder);

    void deleteAllByOrderId(Long orderId);

    @Query("SELECT SUM(orderDetail.total) FROM OrderDetailEntity orderDetail where orderDetail.orderId=?1")
    Long totalPrice(Long orderId);

}
