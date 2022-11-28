package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionStatusEnum {
    CHUAKICHHOAT(0),
    DANGKHUYENMAI(1);

    private final int value;
}
