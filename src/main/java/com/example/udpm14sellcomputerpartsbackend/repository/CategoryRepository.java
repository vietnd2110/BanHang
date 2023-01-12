package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    List<CategoryEntity> findAllByStatusEquals(StatusEnum status);

    Page<CategoryEntity> findByStatusEqualsOrderByIdDesc(StatusEnum status, Pageable pageable);

    Optional<CategoryEntity> findById(Optional<Long> id);

    CategoryEntity findAllById(Long id);

    List<CategoryEntity> findAllByGroupId(Long groupId);


}
