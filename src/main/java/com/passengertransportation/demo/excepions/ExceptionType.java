package com.passengertransportation.demo.excepions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "Route not found"),
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "Ticket not found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request try again"),
    NO_MORE_AVAILABLE_TICKETS(HttpStatus.BAD_REQUEST, "No more available tickets for this route"),
    BUSS_NOT_FOUND(HttpStatus.NOT_FOUND, "Buss with such id not found"),
    PASSENGER_NOT_FOUND(HttpStatus.NOT_FOUND, "Passenger not found"),
    NOT_TICKET_FOR_THIS_PASSENGER(HttpStatus.BAD_REQUEST, "Passenger has no assigned ticket") ;

    private final HttpStatus httpStatus;
    private final String message;
}

