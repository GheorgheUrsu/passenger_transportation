package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.PassengerDTO;
import com.passengertransportation.demo.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    PassengerDTO toDTO(Passenger passenger);
    Passenger fromDTO(PassengerDTO passengerDTO);
}
