package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.StaffDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.StaffEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StaffManagementService {
    List<StaffEntity> getAll();

    StaffDto create(StaffDto staffDto);

    StaffDto update(Long id, StaffDto staffDto);

    void delete(Long id);
}
