package com.example.udpm14sellcomputerpartsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {
    List<RamEntity> findByProductId(Long id);
}
