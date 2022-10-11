package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.GroupComponent;

import java.util.List;

public interface GroupComponentService {
    List<GroupComponentEntity> getAll();

    GroupComponent createComponent(GroupComponent groupComponent);

    GroupComponent updateComponent(Long id, GroupComponent groupComponent);

    GroupComponent deleteComponent(Long id);
}
