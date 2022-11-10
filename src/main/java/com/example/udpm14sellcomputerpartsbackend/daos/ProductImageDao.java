package com.example.udpm14sellcomputerpartsbackend.daos;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductImageDao {

    private final EntityManager entityManager;

    public Page<ProductImageDto> productImage(Pageable pageable) {
        String strQuery =
                "SELECT " +
                        "pro.id AS id,pro.name AS name," +
                        "pro.price AS price,pro.quantity AS quantity," +
                        "pro.create_date AS createDate,pro.update_date AS updateDate," +
                        "pro.description AS description,pro.status AS status, " +
                        "image.link AS imageLink,image.name AS imageName," +
                        "pro.category_id AS categoryId " +
                        "FROM images image " +
                        "RIGHT JOIN products pro ON image.product_id = pro.id ";

        Query query = entityManager.createNativeQuery(strQuery, "ProductImageDto");

        List<ProductImageDto> list = query.getResultList();

        return pageable == null ? new PageImpl<>(list) : new PageImpl<>(list, pageable, pageable.getPageNumber() * pageable.getPageSize());
    }


}
