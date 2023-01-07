package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/category")
@Tag(
        description = "Category controller",
        name = "Các api thể loại"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllById(
            @PathVariable("id") Long id
    ) {
        CategoryDto categoryEntityList = categoryService.getById(id);
        SampleResponse response = SampleResponse
                .builder()
                .success(true)
                .message("Get by id category")
                .data(categoryEntityList)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Danh sách category theo status", description = "Danh sách category theo status")
    @PreAuthorize("permitAll()")
    @GetMapping("/list-status/{status}")
    public ResponseEntity<?> listStatus(
            @PathVariable("status") StatusEnum status
    ) {
        return ResponseEntity.ok(DefaultResponse.success(categoryService.listStatus(status)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/list-group/{groupId}")
    public ResponseEntity<?> listCategoryWithGroupId(
            @PathVariable("groupId") Long groupId
    ){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategoryGroupId(groupId);
        SampleResponse response = SampleResponse
                .builder()
                .success(true)
                .message("Get all category with group id")
                .data(categoryDtoList)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lấy tất cả danh sách và phân trang về thể loại", description = "Lấy tất cả danh sách và phân trang về thể loại")
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllAndPage(
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-size", required = false) Integer pageNumber
    ) {
        Page<CategoryDto> page = categoryService.getAllAndPage(pageSize, pageNumber);
        return ResponseEntity.ok(DefaultPagingResponse.success(page
        ));
    }

    @Operation(summary = "Lấy tất cả danh sách về thể loại", description = "Lấy tất cả danh sách về thể loại")
    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get all category")
                .data(categoryService.getAll())
                .build());
    }

    @Operation(summary = "Thêm mới thể loại", description = "Thêm mới thể loại")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createCategory(
            @ModelAttribute CategoryDto categoryDto,
            @RequestParam(required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(DefaultResponse.success(categoryService.createCategory(categoryDto, file)));
    }

    @Operation(summary = "Cập nhật mới thể loại", description = "Cập nhật thể loại")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @ModelAttribute CategoryDto categoryDto,
            @RequestParam(required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(DefaultResponse.success(categoryService.updateCategory(id, categoryDto,file)));

    }


    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping(value = "/update-image/{id}",consumes = "multipart/form-data")
    public ResponseEntity<?> updateImageCategory(
            @PathVariable("id") Long id,
            @RequestBody MultipartFile file
    ){
        return ResponseEntity.ok(DefaultResponse.success("Upload image success",categoryService.uploadImage(id,file)));
    }


    @Operation(summary = "Xóa thể loại", description = "Xóa thể loại")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }


}
