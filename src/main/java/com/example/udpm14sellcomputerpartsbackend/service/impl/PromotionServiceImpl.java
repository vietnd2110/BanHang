package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.PromotionDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PromotionEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.PromotionRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.PromotionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.example.udpm14sellcomputerpartsbackend.contants.FolderContants.DISCOUNT_PERCENT;


@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;


    @Override
    public PromotionEntity checkPromotion(String code) {
        return promotionRepository.checkPromotion(code);
    }

    @Override
    public long calculatePromotionPrice(long price, PromotionEntity promotion) {
//        long discountValue = promotion.getMaximumDiscountValue();
        long tmp = promotion.getDiscountValue();
        if (promotion.getDiscountType() == DISCOUNT_PERCENT) {
            tmp = price * (100 - promotion.getDiscountValue()) / 100;
        }
//        if (tmp < discountValue) {
//            discountValue = tmp;
//        }
//
//        long promotionPrice = price - discountValue;
//        if (promotionPrice < 0) {
//            promotionPrice = 0;
//        }

        return tmp;
    }

    @Override
    public PromotionEntity createPromotion(PromotionDto promotionDto) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (promotionDto.getExpiredDate().before(now)) {
            throw new BadRequestException("Hạn khuyến mãi không hợp lệ");
        }

        if (promotionDto.getDiscountType() == DISCOUNT_PERCENT) {
            if (promotionDto.getDiscountValue() < 1 || promotionDto.getDiscountValue() > 100) {
                throw new BadRequestException("Mức giảm giá từ 1% - 100%");
            }
            if (promotionDto.getMaxValue() < 1000) {
                throw new BadRequestException("Mức giảm tối đa phải lớn hơn 1000");
            }
        }else{
            if(promotionDto.getDiscountValue() < 1000){
                throw new BadRequestException("Mức giá phải lớn hơn 1000");
            }
        }
        Optional<PromotionEntity> findById = promotionRepository.findByCouponCode(promotionDto.getCode());
        if(!findById.isEmpty()){
            throw new BadRequestException("Mã code đã tồn tại trong hệ thống");
        }

        PromotionEntity promotion = modelMapper.map(promotionDto,PromotionEntity.class);
        promotion.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return promotionRepository.save(promotion);
    }

    @Override
    public PromotionEntity updatePromotion(Long id, PromotionDto promotionDto){
        Optional<PromotionEntity> findById = promotionRepository.findById(id);

        if(findById.isEmpty()){
            throw new BadRequestException("Khuyến mãi không tồn tại");
        }

        if(promotionDto.getDiscountType() == DISCOUNT_PERCENT){
            if(promotionDto.getDiscountValue() < 1 || promotionDto.getDiscountValue() >100){
                throw new BadRequestException("Mức giảm giá từ 1% - 100%");
            }
            if(promotionDto.getMaxValue() < 1000){
                throw new BadRequestException("Mức giảm giá tối đa phải lơn 1000");
            }
        }else{
            if(promotionDto.getDiscountValue() < 1000){
                throw new BadRequestException("Mức giảm giá phải lớn hơn 1000");
            }
        }
        findById = promotionRepository.findByCouponCode(promotionDto.getCode());
        if(!findById.isEmpty() && findById.get().getId() != id){
            throw new BadRequestException("Mã code đã tồn tại");
        }

        PromotionEntity promotion = modelMapper.map(promotionDto,PromotionEntity.class);
        promotion.setCreatedAt(findById.get().getCreatedAt());

        return promotionRepository.save(promotion);
    }

    @Override
    public PromotionEntity getPromotionById(Long id){
        Optional<PromotionEntity> optional = promotionRepository.findById(id);
        if(optional.isEmpty()){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(),"Khuyến mãi không tồn tại");
        }
        return optional.get();
    }

    @Override
    public List<PromotionEntity> findAll(){
        return promotionRepository.findAll();
    }

}
