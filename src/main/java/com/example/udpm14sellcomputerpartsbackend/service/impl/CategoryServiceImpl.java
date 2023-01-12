package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final GroupComponentRepository groupComponentRepository;
    private final CloudinaryServiceImpl cloudinaryService;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            GroupComponentRepository groupComponentRepository,
            ModelMapper modelMapper,
            CloudinaryServiceImpl cloudinaryService
    ) {
        this.categoryRepository = categoryRepository;
        this.groupComponentRepository = groupComponentRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public CategoryDto getById(Long id) {
        CategoryEntity findById = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));

        CategoryEntity categoryEntity = categoryRepository.findAllById(id);
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    @Override
    public List<CategoryEntity> listStatus(StatusEnum status) {
        return categoryRepository.findAllByStatusEquals(status);
    }

    @Override
    public List<CategoryDto> getAllCategoryGroupId(Long groupId) {
        GroupComponentEntity componentEntity = groupComponentRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: " + groupId));
        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByGroupId(groupId);
        return categoryEntityList.stream().map(categoryEntity ->
                modelMapper.map(categoryEntity, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAllByStatusEquals(StatusEnum.ACTIVE);
        return categoryEntityList.stream().map(categoryEntity ->
                modelMapper.map(categoryEntity, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<CategoryDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<CategoryEntity> entityPage = categoryRepository.findByStatusEqualsOrderByIdDesc(StatusEnum.ACTIVE, PageRequest.of(pageSize, pageNumber));
        return entityPage.map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDto.class));
    }

    @Override
    public String uploadImage(Long id, MultipartFile file) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));
        String images = null;
        if (file != null) {
            images = cloudinaryService.uploadImage(file, FolderContants.CATEGORIES_IMAGES_FOLDER);
        } else {
            images = "https://res.cloudinary.com/ducnd1306/image/upload/v1663338173/sample.jpg";
        }
        categoryEntity.setImages(images);
        categoryRepository.save(categoryEntity);
        return images;
    }



    @Override
    public void delete(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));
        categoryEntity.setStatus(StatusEnum.DELETED);
        categoryRepository.save(categoryEntity);
    }


    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto, MultipartFile file) {
        CategoryEntity findById = categoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + id));
        GroupComponentEntity findByGroupId = groupComponentRepository.findById(categoryDto.getGroupId()).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: " + categoryDto.getGroupId()));

        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
        categoryEntity.setId(findById.getId());
        categoryEntity.setCreateDate(findById.getCreateDate());

        String images = cloudinaryService.uploadImage(file,FolderContants.CATEGORIES_IMAGES_FOLDER);
        categoryEntity.setImages(images);

        return modelMapper.map(categoryRepository.save(categoryEntity), CategoryDto.class);

    }


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, MultipartFile file){
        GroupComponentEntity findByGroupId = groupComponentRepository.findById(categoryDto.getGroupId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Group component id not found: " + categoryDto.getGroupId()));

        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
        categoryEntity.setStatus(StatusEnum.ACTIVE);
        String image = cloudinaryService.uploadImage(file,FolderContants.CATEGORIES_IMAGES_FOLDER);
        categoryEntity.setImages(image);

        return modelMapper.map(categoryRepository.save(categoryEntity), CategoryDto.class);
    }


}
