package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VocherStatusEnum {
    INACTIVE(0),
    ACTIVE(1);

    private final int value;
}
