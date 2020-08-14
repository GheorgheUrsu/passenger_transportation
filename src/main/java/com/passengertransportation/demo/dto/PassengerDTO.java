package com.passengertransportation.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerDTO {
    private Long id;
    private String name;
    private String passportData;
    private Date birthDate;
    private int luggageWeight;
    private TicketDTO ticket;
}
