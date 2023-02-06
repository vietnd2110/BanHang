package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    // filter theo gia tung khoang BETWEEN .. AND ...
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "WHERE product.priceNew BETWEEN :start AND :end ")
    public Page<ProductImageDto> listFilterProduct(long start, long end, Pageable page);

    // filter theo color
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN ColorEntity  color ON image.product_id = color.productId " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "WHERE color.id=:id" )
    public Page<ProductImageDto> listFilterProductByColor(Pageable page, Long id);

    // filter theo gia giảm dần
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "ORDER BY product.priceNew DESC ")
    public Page<ProductImageDto> listFilterProductPriceDesc(Pageable page);

    // filter theo gia giảm dần
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "ORDER BY product.priceNew ASC ")
    public Page<ProductImageDto> listFilterProductPriceASC(Pageable page);


    // lấy tất cả danh sách product và ảnh
//    @Query(value = " SELECT " +
//            " pro.id,pro.name,pro.price,pro.quantity,pro.create_date,pro.update_date, " +
//            " pro.description,pro.status,image.link,image.name as imageName,pro.category_id " +
//            " FROM images image " +
//            " RIGHT JOIN products pro ON image.product_id = pro.id ",nativeQuery = true)
//     Page<ProductAndImages> listProduct(Pageable page);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "ORDER BY product.id desc ")
    Page<ProductImageDto> listProduct(Pageable page);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "where product.status=:statusEnum")
    Page<ProductImageDto> listProductByStatus(Pageable page, StatusEnum statusEnum);



    //lấy tất cả danh sách product và ảnh theo id của product
    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "WHERE product.id = :id " +
            "group by product.id")
    public Page<ProductImageDto> listProductId(Long id, Pageable page);


    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id,product.code,product.name,product.price,product.priceNew,product.discount,product.quantity,product.createDate," +
            "product.updateDate,product.description,product.status,image.link,image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "JOIN CategoryEntity category on product.categoryId = category.id " +
            "JOIN BrandEntity brand ON product.brandId = brand.id " +
            "GROUP BY product.id " +
            "order by product.id desc ")
    public Page<ProductImageDto> listProductAndPage(Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id," +
            "product.code," +
            "product.name," +
            "product.price," +
            "product.priceNew," +
            "product.discount," +
            "product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description," +
            "product.status," +
            "image.link," +
            "image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "where CONCAT(product.name, ' ' , product.priceNew, ' ', product.code, ' ') like %?1% " +
            "GROUP BY product.id " +
            "order by product.id desc ")
    public Page<ProductImageDto> searchByName(String name, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id," +
            "product.code," +
            "product.name," +
            "product.price," +
            "product.priceNew," +
            "product.discount," +
            "product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description," +
            "product.status," +
            "image.link," +
            "image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id  where product.categoryId=:id " +
            "group by product.id")
    public Page<ProductImageDto> findByCategory(Long id, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id," +
            "product.code," +
            "product.name," +
            "product.price," +
            "product.priceNew," +
            "product.discount," +
            "product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description," +
            "product.status," +
            "image.link," +
            "image.name,product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id  where product.id=:id " +
            "GROUP BY product.id")
    public Page<ProductImageDto> findByIdProduct(Long id, Pageable pageable);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto(" +
            "product.id," +
            "product.code," +
            "product.name," +
            "product.price," +
            "product.priceNew," +
            "product.discount," +
            "product.quantity," +
            "product.createDate," +
            "product.updateDate," +
            "product.description," +
            "product.status," +
            "image.link," +
            "image.name," +
            "product.categoryId,category.name,product.brandId, brand.brandName) " +
            "FROM ImageEntity image " +
            "INNER JOIN ProductEntity product ON image.product_id = product.id " +
            "INNER JOIN CategoryEntity category on product.categoryId = category.id " +
            "INNER JOIN BrandEntity brand ON product.brandId = brand.id " +
            "where product.brandId=:id")
    public Page<ProductImageDto> findByBrand(Long id, Pageable pageable);

    List<ProductEntity> findAllById(Long id);

    ProductEntity findAllByCode(String maSP);
    Integer countByCode(String ma);

}
