package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface FilterProductService {
    Page<ProductCaseDto> filterProductCase(Long category, long start_price, long end_price, int page, int pageNumber);

    Page<ProductImageDto> filterProductByPrice(long start_price, long end_price, int page, int pageNumber);

    Page<ProductImageDto> listFilterProductPriceDesc(int page, int pageNumber);

    Page<ProductImageDto> listFilterProductPriceAsc(int page, int pageNumber);

    Page<ProductImageDto> listFilterProductByColor(int page, int pageNumber, Long id);
}
