package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.daos.ProductImageDao;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.BrandEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CategoryEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.VoucherEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.BrandRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.CategoryRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.VoucherRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;
    private final VoucherRepository voucherRepository;
    private final ModelMapper modelMapper;
    private final ProductImageDao productImageDao;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              VoucherRepository voucherRepository,
                              BrandRepository brandRepository,
                              ModelMapper modelMapper,
                              ProductImageDao productImageDao) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.voucherRepository = voucherRepository;
        this.modelMapper = modelMapper;
        this.productImageDao = productImageDao;
    }

    @Override
    public Page<ProductEntity> findAll(Integer page, Integer pageNumber) {
//        return productImageDao.productImage(PageRequest.of(page,pageNumber));
        return productRepository.findAll(PageRequest.of(page,pageNumber));
    }

    @Override
    public ProductEntity getOne(Long productId){
        ProductEntity findById = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));
        return productRepository.findById(findById.getId()).get();
    }

    @Override
    public Page<ProductImageDto> findAllByIDProduct(Long productId,Integer page, Integer pageNumber){
        ProductEntity findById = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));
        return productRepository.listProductId(productId,PageRequest.of(page,pageNumber));
    }

    @Override
    public Page<ProductImageDto> search(String name, Integer pageSize, Integer pageNumber) {

        Page<ProductImageDto> page = null;
        if(!StringUtils.hasText(name)){
            page = productRepository.listProductAndPage(PageRequest.of(pageSize,pageNumber));
        }else{
            page = productRepository.searchByName(name, PageRequest.of(pageSize, pageNumber));
        }
        return page;
    }

    @Override
    public Page<ProductImageDto> findByCategory(Long id, Integer pageSize, Integer pageNumber) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found:"+id));
        return productRepository.findByCategory(id, PageRequest.of(pageSize, pageNumber));
    }

    @Override
    public Page<ProductImageDto> findByBrand(Long id, Integer pageSize, Integer pageNumber) {
        BrandEntity brandEntity = brandRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Brand id not found:" + id));
        return productRepository.findByBrand(id, PageRequest.of(pageSize, pageNumber));
    }


    @Override
    public Page<ProductImageDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        return productRepository.listProductAndPage(PageRequest.of(pageSize, pageNumber));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + productDto.getCategoryId()));
        VoucherEntity voucherEntity = voucherRepository.findById(productDto.getVoucherId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Voucher id not found: " + productDto.getVoucherId()));

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

        productEntity.setCategoryId(categoryEntity.getId());
        productEntity.setVoucherId(voucherEntity.getId());
        productEntity.setStatus(StatusEnum.ACTIVE);

        return modelMapper.map(productRepository.save(productEntity), ProductDto.class);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        CategoryEntity findCate = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Category id not found: " + productDto.getCategoryId()));
        VoucherEntity voucherEntity = voucherRepository.findById(productDto.getVoucherId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Voucher id not found: " + productDto.getVoucherId()));
        ProductEntity find = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found:" + id));

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productEntity.setId(find.getId());
        productEntity.setStatus(StatusEnum.ACTIVE);
        productEntity.setCreateDate(find.getCreateDate());
        productEntity.setCategoryId(findCate.getId());
        productEntity.setVoucherId(voucherEntity.getId());

        return modelMapper.map(productRepository.save(productEntity), ProductDto.class);
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found:" + id));
        productRepository.deleteById(productEntity.getId());
    }

}
