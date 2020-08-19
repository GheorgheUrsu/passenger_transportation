package com.passengertransportation.demo.dto;

import lombok.Data;

@Data
public class PassengerDTO {
    private Long id;
    private String name;
    private String passportData;
    private String birthDate;
    private Integer luggageWeight;
}
