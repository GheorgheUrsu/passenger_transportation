package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.PassengerDTO;
import com.passengertransportation.demo.model.Passenger;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);


    PassengerDTO toDTO(Passenger passenger, @Context CycleAvoidingMappingContex context);

    @InheritInverseConfiguration
    Passenger fromDTO(PassengerDTO passengerDTO, @Context CycleAvoidingMappingContex context);

}
