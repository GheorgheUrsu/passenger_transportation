package com.passengertransportation.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BussDTO {
    private Long id;
    private String regNumber;
    private Date lastInspection;
    private String bussType;
    private RouteDTO route;
}
