package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PromotionDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PromotionEntity;

import javax.mail.MessagingException;
import java.util.List;

public interface PromotionService {
    PromotionEntity checkPromotion(String code);

    long calculatePromotionPrice(long price, PromotionEntity promotion);

    PromotionEntity createPromotion(PromotionDto promotionDto);

    PromotionEntity updatePromotion(Long id, PromotionDto promotionDto);

    PromotionEntity getPromotionById(Long id);

    List<PromotionEntity> findAll();
}
