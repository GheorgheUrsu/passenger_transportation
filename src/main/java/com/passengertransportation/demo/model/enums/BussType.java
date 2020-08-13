package com.passengertransportation.demo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BussType {
    MINIVAN(8),
    STANDARD(16),
    DOUBLE_DECKER(30);

    private final Integer passengerCapacity;
}
