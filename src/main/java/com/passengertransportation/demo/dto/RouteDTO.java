package com.passengertransportation.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RouteDTO {
    private Long id;
    private String startLocation;
    private String arrivalDestination;
    private LocalDateTime startDate;
    private LocalDateTime arrivalDate;
    private BussDTO buss;
}
