package com.example.udpm14sellcomputerpartsbackend.daos;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductFavoriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductFavoriteDao {

    private final EntityManager entityManager;

    public List<?> productFavorites() {
        String strQuery = "select * from product_favorite";

        Query query = entityManager.createNativeQuery(strQuery, "ProductFavoriteDto");
        List<ProductFavoriteDto> list = query.getResultList();
        System.out.println(list);
        return list;
    }

}
