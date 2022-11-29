package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM promotion WHERE is_active = true AND coupon_code = ?1")
    PromotionEntity checkPromotion(String code);

    Optional<PromotionEntity> findByCouponCode(String couponCode);
}
