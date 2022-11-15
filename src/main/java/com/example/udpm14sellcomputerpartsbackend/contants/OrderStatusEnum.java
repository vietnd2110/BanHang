package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    CHOXACNHAN(0),
    DAXACNHAN(1),
    CHOTHANHTOAN(2),
    DANGVANCHUYEN(3),
    DAGIAO(4),
    DAHUY(5);

    private final int value;

}
