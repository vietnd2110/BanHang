package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findAllByUserId(Long id);
    CartEntity findAllByUserIdAndProductId(Long id, Long productId);

    @Query("SELECT SUM(c.total) FROM CartEntity c WHERE c.userId=?1")
    BigDecimal sumPrice(Long id);

    @Query("SELECT COUNT(c.productId) FROM CartEntity c WHERE c.userId = ?1")
    Integer countCart(Long id);
}
