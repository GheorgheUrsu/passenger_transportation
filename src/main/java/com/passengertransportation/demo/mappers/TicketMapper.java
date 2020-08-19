package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.model.Ticket;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);


    TicketDTO toDTO(Ticket ticket, @Context CycleAvoidingMappingContex context);

    @InheritInverseConfiguration
    Ticket fromDTO(TicketDTO ticketDTO, @Context CycleAvoidingMappingContex context);
}
