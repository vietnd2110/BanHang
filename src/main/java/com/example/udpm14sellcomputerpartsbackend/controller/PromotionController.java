package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CheckPromotionDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.PromotionDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PromotionEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/promotion")
@AllArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;


    @GetMapping("/check")
    public ResponseEntity<?> checkPromotion(
            @RequestParam(name = "code") String code){
        if(code == null || code == ""){
            throw new BadRequestException("Mã code trống");
        }

        PromotionEntity promotion = promotionService.checkPromotion(code);
        if(promotion == null){
            throw new BadRequestException("Mã khuyến mãi không tồn tại hoặc chưa được kích hoạt");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (promotion.getExpiredAt().before(now)) {
            throw new BadRequestException("Mã khuyến mãi hết hạn");
        }
        CheckPromotionDto checkPromotion = new CheckPromotionDto();
        checkPromotion.setDiscountType(promotion.getDiscountType());
        checkPromotion.setDiscountValue(promotion.getDiscountValue());
        checkPromotion.setMaximumDiscountValue(promotion.getMaximumDiscountValue());
        return ResponseEntity.ok(DefaultResponse.success(checkPromotion));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PromotionDto promotionDto){
        return ResponseEntity.ok(promotionService.createPromotion(promotionDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid PromotionDto promotionDto){
        return ResponseEntity.ok(promotionService.updatePromotion(id,promotionDto));
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
//        return ResponseEntity.ok();
//    }



}
