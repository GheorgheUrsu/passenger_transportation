package com.passengertransportation.demo.excepions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionType {

    ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "Route not found"),
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "Ticket not found");

    private final HttpStatus httpStatus;
    private final String message;
}
