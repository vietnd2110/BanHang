package com.example.udpm14sellcomputerpartsbackend.repository;

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
    @Query("SELECT o FROM OrderDetailEntity o")
    Page<OrderDetailEntity> getAllAndPage(Pageable pageable);

    Page<OrderDetailEntity> findAllByOrderId(Long id, Pageable pageable);

    List<OrderDetailEntity> findAllByUserId(Long id);


}
