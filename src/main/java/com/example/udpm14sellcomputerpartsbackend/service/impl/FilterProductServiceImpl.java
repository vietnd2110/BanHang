package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.daos.FilterProductDao;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.repository.FilterRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.FilterProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class FilterProductServiceImpl implements FilterProductService {

    private final FilterProductDao filterProductDao;
    private final ProductRepository productRepository;
    private final FilterRepository filterRepository;

    public FilterProductServiceImpl(
            FilterProductDao filterProductDao,
            ProductRepository productRepository,
            FilterRepository filterRepository
    ){
        this.filterProductDao = filterProductDao;
        this.productRepository = productRepository;
        this.filterRepository = filterRepository;
    }

    @Override
    public Page<ProductCaseDto> filterProductCase(Long category, long start_price, long end_price, int page, int pageNumber){
        return filterRepository.listFilterProductCase(category,start_price,end_price,PageRequest.of(page,pageNumber));
    }



    @Override
    public Page<ProductImageDto> filterProductByPrice(long start_price, long end_price, int page, int pageNumber){
       return productRepository.listFilterProduct(start_price,end_price,PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> listFilterProductPriceDesc(int page, int pageNumber){
        return productRepository.listFilterProductPriceDesc(PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> listFilterProductPriceAsc(int page, int pageNumber){
        return productRepository.listFilterProductPriceASC(PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> listFilterProductByColor(int page, int pageNumber, Long id) {
        return productRepository.listFilterProductByColor(PageRequest.of(page,pageNumber), id);
    }


}
