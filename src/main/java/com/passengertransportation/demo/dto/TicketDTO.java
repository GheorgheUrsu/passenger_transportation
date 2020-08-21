package com.passengertransportation.demo.dto;

import com.passengertransportation.demo.model.enums.TicketType;
import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Integer price;
    private TicketType ticketType;
    private RouteDTO route;
    private PassengerDTO passenger;
}
