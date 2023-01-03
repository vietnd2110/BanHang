package com.example.udpm14sellcomputerpartsbackend.daos;

import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ProductBanChayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticalDao {
    private final EntityManager entityManager;

    public List<ProductBanChayDto> topProduct() {
        String StrQuery = "SELECT product_id as id,name as name, sum(quantity) as quantity from order_details group by name order by sum(quantity) desc";
        Query query = entityManager.createNativeQuery(StrQuery, "TopProduct").setMaxResults(10);
        List<ProductBanChayDto> list = query.getResultList();

        return list;
    }

}
