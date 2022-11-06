package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.BrandEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.BrandRepository;
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

    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<GroupComponentDto> getAll() {
        List<GroupComponentEntity> groupComponentEntity = groupComponentRepository.findAll();
        return groupComponentEntity.stream().map(grEntity -> modelMapper
                .map(grEntity, GroupComponentDto.class)).collect(Collectors.toList());
    }

    @Override
    public GroupComponentDto createComponent(GroupComponentDto groupComponent) {
        BrandEntity brandEntity = brandRepository.findById(groupComponent.getBrandId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Brand id not found: "+ groupComponent.getBrandId()));
        GroupComponentEntity gr = modelMapper.map(groupComponent, GroupComponentEntity.class);
        return modelMapper.map(groupComponentRepository.save(gr),GroupComponentDto.class);
//        GroupComponentEntity groupComponentEntity = new GroupComponentEntity();
//        groupComponentEntity.setName(groupComponent.getName());
//        groupComponentEntity.setBrandId(groupComponent.getBrandId());
//        groupComponentRepository.save(groupComponentEntity);
//        return groupComponent;
    }

    @Override
    public GroupComponentDto updateComponent(Long id, GroupComponentDto groupComponent) {
        BrandEntity brandEntity = brandRepository.findById(groupComponent.getBrandId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Brand id not found: "+ groupComponent.getBrandId()));
        GroupComponentEntity findGr = groupComponentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Component id not found: " + id));
        GroupComponentEntity groupComponentEntity = modelMapper.map(groupComponent, GroupComponentEntity.class);
        groupComponentEntity.setId(findGr.getId());

        return modelMapper.map(groupComponentRepository.save(groupComponentEntity),GroupComponentDto.class);

        //        GroupComponentEntity groupComponentEntity = groupComponentRepository.findById(id).
//                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Component id not found: " + id));
//        BrandEntity brandEntity = brandRepository.findById(groupComponent.getBrandId()).
//                orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Brand id not found: " + groupComponent.getBrandId()));
//        groupComponentEntity.setName(groupComponent.getName());
//        groupComponentRepository.save(groupComponentEntity);
//        return groupComponent;
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
