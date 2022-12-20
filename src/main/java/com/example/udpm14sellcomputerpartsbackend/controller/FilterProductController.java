package com.example.udpm14sellcomputerpartsbackend.controller;


import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.service.FilterProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
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

//    @Operation(summary = "Lọc tất cả danh sách theo khoảng giá và phân trang theo category id", description = "Lọc tất cả danh sách theo khoảng giá và phân trang")
//    @GetMapping("/{id}")
//    public ResponseEntity filterProductCases(
//            @PathVariable("id") Long cateId,
//            @RequestParam("s_price") long s_price,
//            @RequestParam("e_price") long e_price,
//            @RequestParam(value = "page",defaultValue = "0") int page,
//            @RequestParam(value = "page-size") int pageNumber
//    ){
//        DefaultPagingResponse defaultPagingResponse =
//                DefaultPagingResponse.success(filterProductService.filterProductCase(cateId,s_price, e_price,page,pageNumber));
//        return ResponseEntity.ok(defaultPagingResponse);
//    }

//    /.//////
    @Operation(summary = "Lọc tất cả danh sách theo khoảng giá và phân trang", description = "Lọc tất cả danh sách theo khoảng giá và phân trang")
    @GetMapping()
    public ResponseEntity filterProductByPrice(
            @RequestParam("s_price") long s_price,
            @RequestParam("e_price") long e_price,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page-size") int pageNumber
            ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.filterProductByPrice(s_price, e_price,page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lọc tất cả danh sách theo giá giảm dần và phân trang", description = "Lọc tất cả danh sách theo giá giảm dần và phân trang")
    @GetMapping("/price-desc")
    public ResponseEntity filterProductPriceDesc(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam("page-size") int pageNumber
    ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.listFilterProductPriceDesc(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lọc tất cả danh sách theo giá tăng dần và phân trang", description = "Lọc tất cả danh sách theo giá tăng dần và phân trang")
    @GetMapping("/price-asc")
    public ResponseEntity filterProductPriceASC(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam("page-size") int pageNumber
    ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.listFilterProductPriceAsc(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Lọc tất cả danh sách theo color", description = "Lọc tất cả danh sách theo color")
    @GetMapping("/color/{id}")
    public ResponseEntity filterProductPriceByColor(
            @PathVariable("id") Long id,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam("page-size") int pageNumber
    ){
        DefaultPagingResponse defaultPagingResponse =
                DefaultPagingResponse.success(filterProductService.listFilterProductByColor(page,pageNumber, id));
        return ResponseEntity.ok(defaultPagingResponse);
    }


}
