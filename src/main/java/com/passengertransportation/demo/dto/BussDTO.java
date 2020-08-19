package com.passengertransportation.demo.dto;

import com.passengertransportation.demo.model.enums.BussType;
import lombok.Data;

@Data
public class BussDTO {
    private Long id;
    private String regNumber;
    private String lastInspection;
    private BussType bussType;
}
