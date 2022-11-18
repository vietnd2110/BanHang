package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    CHOXACNHAN(0),
    DANGSULY(1),
    DANGVANCHUYEN(2),
    DAGIAO(3),
    DAHUY(4);

    private final int value;

}
