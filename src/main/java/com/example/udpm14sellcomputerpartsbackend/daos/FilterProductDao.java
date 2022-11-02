package com.example.udpm14sellcomputerpartsbackend.daos;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.payload.request.FilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilterProductDao {

    private final EntityManager entityManager;

    public List<?> productByFilter(BigDecimal start_price, BigDecimal end_price){
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT * FROM products pro ");
//        sb.append(" WHERE pro.price ");
//
//        if(start_price != null){
//            sb.append("BETWEEN :start AND :end");
//        }

        String strQuery =
                ""
                        +"SELECT * FROM products pro "
                        +"inner join images img on pro.id = img.id "
                        +"WHERE pro.price BETWEEN :start AND :end ";



        Query query = entityManager.createNativeQuery(strQuery, ProductImageDto.class);
        query.setParameter("start",start_price);
        query.setParameter("end",end_price);

        List<?> list = query.getResultList();

        return list;
    }


}
