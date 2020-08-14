package com.passengertransportation.demo.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Integer price;
    private String ticketType;
    private RouteDTO route;
    private PassengerDTO passenger;
}
