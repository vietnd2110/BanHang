package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> {

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto(" +
            "color.id, color.colorName, color.productId, pro.name) " +
            "FROM ColorEntity color " +
            "INNER JOIN ProductEntity pro ON color.productId = pro.id " +
            "order by color.id DESC ")
    List<ColorDto> getAll();

}
