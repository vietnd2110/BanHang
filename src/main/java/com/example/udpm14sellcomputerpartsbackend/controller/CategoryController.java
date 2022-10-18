package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAndPage(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber
    ) {
        Page<CategoryDto> page = categoryService.getAllAndPage(pageSize,pageNumber);
        return ResponseEntity.ok(DefaultPagingResponse.success(page
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get all category")
                .data(categoryService.getAll())
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return ResponseEntity.ok(
                DefaultResponse.success(categoryService.create(categoryDto)
                ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(categoryService.update(id, categoryDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }


}
