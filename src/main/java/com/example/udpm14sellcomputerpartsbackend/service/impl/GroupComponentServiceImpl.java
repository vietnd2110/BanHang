package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.BrandEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;
import com.example.udpm14sellcomputerpartsbackend.repository.BrandRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.GroupComponentRepository;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GroupComponentServiceImpl implements GroupComponentService {

    private final GroupComponentRepository groupComponentRepository;

    private final BrandRepository brandRepository;

    @Override
    public List<GroupComponentEntity> getAll() {
        return groupComponentRepository.findAll();
    }

    @Override
    public GroupComponentDto createComponent(GroupComponentDto groupComponent) {
        GroupComponentEntity groupComponentEntity = new GroupComponentEntity();
        groupComponentEntity.setName(groupComponent.getName());
        groupComponentRepository.save(groupComponentEntity);
        return groupComponent;
    }

    @Override
    public GroupComponentDto updateComponent(Long id, GroupComponentDto groupComponent) {
        GroupComponentEntity groupComponentEntity = groupComponentRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Component id not found: " + id));
        groupComponentEntity.setName(groupComponent.getName());
        groupComponentRepository.save(groupComponentEntity);
        return groupComponent;
    }

    @Override
    public void deleteComponent(Long id) {
        GroupComponentEntity findById = groupComponentRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Component id not found: " + id));
        groupComponentRepository.deleteById(findById.getId());
    }
}
