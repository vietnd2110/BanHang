package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.MainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainRepository extends JpaRepository<MainEntity, Long> {
    @Query("select m from MainEntity m")
    Page<MainEntity> getAllAndPage(Pageable pageable);

    Page<MainEntity> getAllByProductId(Long id, Pageable pageable);
}
