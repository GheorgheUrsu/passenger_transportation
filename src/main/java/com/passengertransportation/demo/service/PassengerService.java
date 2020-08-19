package com.passengertransportation.demo.service;

import com.passengertransportation.demo.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
    List<PassengerDTO> getAllPassengers();

    PassengerDTO deletePassengerById(Long passengerID);

    PassengerDTO updatePassenger(Long passengerID, PassengerDTO passengerDTO);

    PassengerDTO findPassengerByID(Long passengerID);

    PassengerDTO createPassenger(PassengerDTO passengerDTO);
}
