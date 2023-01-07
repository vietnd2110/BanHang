package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    DONCHO(0),
    DONLE(1),
    TAIQUAY(2),
    DONGIAO(3);

    private final int value;
}
