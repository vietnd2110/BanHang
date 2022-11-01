package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Lấy tất cả danh sách san phẩm", description = "Lấy tất cả danh sách san phẩm")
    @GetMapping("/list")
    public ResponseEntity<?> findAll(
            @RequestParam("page") Integer page,
            @RequestParam("page-number") Integer pageNumber
    ) {
       DefaultPagingResponse defaultPagingResponse =
               DefaultPagingResponse.success(productService.findAll(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên product ", description = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên product ")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllByIdProduct(
            @PathVariable("id") Long id,
            @RequestParam("page") Integer page,
            @RequestParam("page-number") Integer pageNumber
    ){
      //  Page<ProductImageDto> productImageDtos = productService.findAllByIDProduct(id,page,pageSize);

        DefaultPagingResponse pagingResponse =
                DefaultPagingResponse.success(productService.findAllByIDProduct(id,page,pageNumber));
        return ResponseEntity.ok(pagingResponse);
    }



    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber
    ) {
        Page<ProductImageDto> search = productService.search(name, pageSize, pageNumber);
        SampleResponse response = SampleResponse
                .builder()
                .success(true)
                .message("Get All")
                .data(search)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAndPage(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber
    ) {
        Page<ProductImageDto> page = productService.getAllAndPage(pageSize, pageNumber);

        return ResponseEntity.ok(DefaultPagingResponse.success(page));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllByCategory(
            @PathVariable("id") Long cid,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber
    ) {
        Page<ProductImageDto> page = productService.findByCategory(cid, pageSize, pageNumber);

        return ResponseEntity.ok(DefaultPagingResponse.success(page));

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Create success")
                .data(productService.update(id, productDto))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto productDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Update success")
                .data(productService.create(productDto))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }


}
