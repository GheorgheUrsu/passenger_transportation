package com.passengertransportation.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassengerDTO {
    private Long id;
    private String name;
    private String passportData;
    private LocalDate birthDate;
    private Integer luggageWeight;
}
