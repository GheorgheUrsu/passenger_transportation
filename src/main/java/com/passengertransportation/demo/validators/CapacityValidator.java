package com.passengertransportation.demo.validators;

import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.BussType;

import java.util.Set;

public class CapacityValidator {

    public static Boolean hasSeats(Set<Ticket> tickets, BussType bussType) {
        return tickets.size() < bussType.getPassengerCapacity();
    }
}

