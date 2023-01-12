package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {


    CHOXACNHAN(0),
    DANGXULY(1),
    DANGVANCHUYEN(2),
    DAGIAO(3),
    DAHUY(4),
    DAHOANTHANH(5)

    ;

    private final int value;

}
