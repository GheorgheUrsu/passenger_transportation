package com.passengertransportation.demo.dto;

import com.passengertransportation.demo.model.enums.BussType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BussDTO {
    private Long id;
    private String regNumber;
    private LocalDate lastInspection;
    private BussType bussType;
}
