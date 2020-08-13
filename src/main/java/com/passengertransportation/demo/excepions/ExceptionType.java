package com.passengertransportation.demo.excepions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "Route not found"),
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "Ticket not found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request try again");

    private final HttpStatus httpStatus;
    private final String message;
}
