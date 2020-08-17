package com.passengertransportation.demo.service;


import com.passengertransportation.demo.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllSoldTickets();
}
