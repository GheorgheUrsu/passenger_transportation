package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.repo.TicketRepository;
import com.passengertransportation.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<TicketDTO> getAllSoldTickets() {
        return null;
    }
}
