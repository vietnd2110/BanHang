package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByVerificationCode(String verificationCode);

    Optional<UserEntity> findById(Long id);

    @Query("select u FROM UserEntity u where u.role=1")
    Page<UserEntity> findAllByStaff(Pageable pageable);
    @Query("select u FROM UserEntity u where u.role=2")
    Page<UserEntity> findAllByAdmin(Pageable pageable);
    @Query("select u FROM UserEntity u where u.role=0")
    Page<UserEntity> findAllByCustomer(Pageable pageable);

}
