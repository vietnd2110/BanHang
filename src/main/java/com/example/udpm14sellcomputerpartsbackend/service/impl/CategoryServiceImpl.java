package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CategoryEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.CategoryRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.GroupComponentRepository;
import com.example.udpm14sellcomputerpartsbackend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final GroupComponentRepository groupComponentRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            GroupComponentRepository groupComponentRepository,
            ModelMapper modelMapper
    ){
        this.categoryRepository = categoryRepository;
        this.groupComponentRepository = groupComponentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryDto getById(Long id){
        CategoryEntity findById = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: "+id));

        CategoryEntity categoryEntity = categoryRepository.findAllById(id);
        return modelMapper.map(categoryEntity,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategoryGroupId(Long groupId){
        GroupComponentEntity componentEntity = groupComponentRepository.findById(groupId)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: " + groupId));
        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByGroupId(groupId);
        return categoryEntityList.stream().map(categoryEntity ->
                modelMapper.map(categoryEntity,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll(){
        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByStatusEquals(StatusEnum.ACTIVE);
        return categoryEntityList.stream().map(categoryEntity->
                modelMapper.map(categoryEntity,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<CategoryDto> getAllAndPage(Integer pageSize, Integer pageNumber){
        Page<CategoryEntity> entityPage = categoryRepository.findByStatusEquals(StatusEnum.ACTIVE,PageRequest.of(pageSize,pageNumber));
        return entityPage.map(categoryEntity -> modelMapper.map(categoryEntity,CategoryDto.class));
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto){
        GroupComponentEntity findByGroupId = groupComponentRepository.findById(categoryDto.getGroupId())
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: "+ categoryDto.getGroupId()));

        CategoryEntity categoryEntity = modelMapper.map(categoryDto,CategoryEntity.class);
        categoryEntity.setStatus(StatusEnum.ACTIVE);

        return modelMapper.map(categoryRepository.save(categoryEntity),CategoryDto.class);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto){
        CategoryEntity findById = categoryRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));
        GroupComponentEntity findByGroupId = groupComponentRepository.findById(categoryDto.getGroupId()).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: "+categoryDto.getGroupId()));

        CategoryEntity categoryEntity = modelMapper.map(categoryDto,CategoryEntity.class);
        categoryEntity.setId(findById.getId());
        categoryEntity.setStatus(findById.getStatus());
        categoryEntity.setCreateDate(findById.getCreateDate());

        return modelMapper.map(categoryRepository.save(categoryEntity),CategoryDto.class);

    }

    @Override
    public void delete(Long id){
        CategoryEntity findById = categoryRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));
        categoryRepository.deleteById(findById.getId());
    }


}
