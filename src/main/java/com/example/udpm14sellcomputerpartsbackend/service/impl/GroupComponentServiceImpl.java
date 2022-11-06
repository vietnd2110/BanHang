package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.GroupComponentRepository;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GroupComponentServiceImpl implements GroupComponentService {

    private final GroupComponentRepository groupComponentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<GroupComponentDto> getAll() {
        List<GroupComponentEntity> groupComponentEntity = groupComponentRepository.findAll();
        return groupComponentEntity.stream().map(grEntity -> modelMapper
                .map(grEntity, GroupComponentDto.class)).collect(Collectors.toList());
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

    @Override
    public GroupComponentDto findById(Long id) {
        GroupComponentEntity findById = groupComponentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Component id not found: " + id));
        return modelMapper.map(groupComponentRepository.findById(findById.getId()), GroupComponentDto.class);
    }
}
