package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;

import java.util.List;

public interface GroupComponentService {
    List<GroupComponentDto> getAll();

    GroupComponentDto createComponent(GroupComponentDto groupComponent);

    GroupComponentDto updateComponent(Long id, GroupComponentDto groupComponent);

    void deleteComponent(Long id);

    GroupComponentDto findById(Long id);
}
