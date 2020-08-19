package com.passengertransportation.demo.dto;

import lombok.Data;

@Data
public class RouteDTO {
    private Long id;
    private String startLocation;
    private String arrivalDestination;
    private String startDate;
    private String arrivalDate;
    private BussDTO buss;
}
