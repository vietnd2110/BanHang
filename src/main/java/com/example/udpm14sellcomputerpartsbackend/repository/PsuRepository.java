package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.PsuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsuRepository extends JpaRepository<PsuEntity, Long> {
    List<PsuEntity> findByProductId(Long id);
}
