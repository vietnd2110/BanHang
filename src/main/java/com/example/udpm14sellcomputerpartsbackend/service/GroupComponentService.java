package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;

import java.util.List;

public interface GroupComponentService {
    List<GroupComponentEntity> getAll();

    GroupComponentDto createComponent(GroupComponentDto groupComponent);

    GroupComponentDto updateComponent(Long id, GroupComponentDto groupComponent);

    GroupComponentDto deleteComponent(Long id);
}
