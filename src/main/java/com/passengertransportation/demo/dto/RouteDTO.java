package com.passengertransportation.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class RouteDTO {
    private Long id;
    private Long startLocation;
    private String arrivalDestination;
    private LocalDateTime startDateTime;
    private LocalDateTime arrivalDateTime;
    private Set<TicketDTO> tickets;
    private BussDTO buss;
}
