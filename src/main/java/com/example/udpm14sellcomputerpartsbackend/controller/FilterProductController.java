package com.example.udpm14sellcomputerpartsbackend.controller;


import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.FilterProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/filter")
@Tag(
        description = "Filter controller",
        name = "Các api về filter"
)
public class FilterProductController {

    private final FilterProductService filterProductService;

    public FilterProductController(
            FilterProductService filterProductService
    ){
        this.filterProductService = filterProductService;
    }

    @Operation(summary = "Lọc tất cả danh sách theo khoảng giá và phân trang", description = "Lọc tất cả danh sách theo khoảng giá và phân trang")
    @GetMapping("")
    public ResponseEntity filterProductByPrice(
            @RequestParam("s_price") BigDecimal s_price,
            @RequestParam("e_price") BigDecimal e_price,
            @RequestParam("page") Integer page,
            @RequestParam("page-number") Integer pageNumber
            ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.filterProductByPrice(s_price, e_price,page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lọc tất cả danh sách theo giá giảm dần và phân trang", description = "Lọc tất cả danh sách theo giá giảm dần và phân trang")
    @GetMapping("/price-desc")
    public ResponseEntity filterProductPriceDesc(
            @RequestParam("page") Integer page,
            @RequestParam("page-number") Integer pageNumber
    ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.listFilterProductPriceDesc(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lọc tất cả danh sách theo giá tăng dần và phân trang", description = "Lọc tất cả danh sách theo giá tăng dần và phân trang")
    @GetMapping("/price-asc")
    public ResponseEntity filterProductPriceASC(
            @RequestParam("page") Integer page,
            @RequestParam("page-number") Integer pageNumber
    ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.listFilterProductPriceAsc(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }


}