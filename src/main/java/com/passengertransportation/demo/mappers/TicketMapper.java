package com.passengertransportation.demo.mappers;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDTO toDTO(Ticket ticket);
    Ticket fromDTO(TicketDTO ticketDTO);
}
