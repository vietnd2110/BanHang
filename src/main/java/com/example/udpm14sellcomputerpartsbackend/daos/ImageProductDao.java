package com.example.udpm14sellcomputerpartsbackend.daos;


import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductFavoriteDto;
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
public class ImageProductDao {

    private final EntityManager entityManager;

    public Page<ImageProductDto> imageProduct(Pageable pageable) {
        String strQuery =
                "" +
                "SELECT image.id AS id," +
                        "image.name AS name," +
                        "image.link AS link," +
                        "product.id as productId," +
                        "product.name as productName " +
                "FROM images image " +
                "LEFT JOIN products product ON image.product_id = product.id";

        Query query = entityManager.createNativeQuery(strQuery, "ImageProductDto");

        List<ImageProductDto> list = query.getResultList();

        return pageable == null ? new PageImpl<>(list) : new PageImpl<>(list, pageable, pageable.getPageNumber() * pageable.getPageSize());
    }

}
