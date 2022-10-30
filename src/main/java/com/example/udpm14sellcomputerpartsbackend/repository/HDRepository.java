package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.HdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HDRepository extends JpaRepository<HdEntity, Long> {
    List<HdEntity> findByProductId(Long id);
}
