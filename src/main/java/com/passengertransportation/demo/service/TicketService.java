package com.passengertransportation.demo.service;


import com.passengertransportation.demo.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllSoldTickets();

    TicketDTO deleteTicketByID(Long ticketID);

    TicketDTO updateTicket(TicketDTO ticketDTO, Long ticketID);

    List<TicketDTO> addAllTickets(List<TicketDTO> ticketDTOS);

    TicketDTO findById(Long ticketID);
}
