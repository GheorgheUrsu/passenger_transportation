package com.passengertransportation.demo.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BussType {

    MINIVAN(8),
    STANDARD(16),
    DOUBLE_DECKER(30);

    private final Integer passengerCapacity;


}