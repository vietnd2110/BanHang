package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffManagementRepository extends JpaRepository<StaffEntity, Long> {
    StaffEntity findByUsername(String username);

    StaffEntity findByEmail(String email);
}
