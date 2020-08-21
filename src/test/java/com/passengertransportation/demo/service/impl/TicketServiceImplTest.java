package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.BussType;
import com.passengertransportation.demo.repo.BussRepository;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import com.passengertransportation.demo.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    private final static Long ID = 1L;
    private TicketService service;
    private Ticket ticket;
    private TicketDTO dto;

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private RouteRepository routeRepository;
    @Mock
    private BussRepository bussRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new TicketServiceImpl(ticketRepository, routeRepository, bussRepository);

        ticket = new Ticket();
        ticket.setId(ID);
        ticket.setPrice(150);

        dto = new TicketDTO();
        dto.setId(ID);
        dto.setPrice(150);
    }

    @Test
    void getAllSoldTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        when(ticketRepository.findAll()).thenReturn(ticketList);
        List<TicketDTO> dtos = service.getAllSoldTickets();

        assertEquals(dtos.size(), ticketList.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void deleteTicketByID() {
        when(ticketRepository.findById(ID)).thenReturn(Optional.of(ticket));

        ticketRepository.deleteById(ID);
        verify(ticketRepository, times(1)).deleteById(ID);

        TicketDTO dto = service.deleteTicketByID(ID);

        assertEquals(dto.getId(), ticket.getId());
    }

    @Test
    void addOneOrMoreTickets() {
        Route route = new Route();
        route.setId(ID);
        route.setArrivalDate("2020-09-19 12:00");
        route.setStartDate("2020-09-18 12:00");
        ticket.setRoute(route);
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket);
        when(ticketRepository.findAllByRouteId(ID)).thenReturn(tickets);

        Buss buss = new Buss();
        buss.setId(ID);
        buss.setBussType(BussType.MINIVAN);
        buss.setLastInspection("2020-08-08");
       // buss.setRoute(route);
        when(bussRepository.findById(ID)).thenReturn(Optional.of(buss));

        List<TicketDTO> dtos = new ArrayList<>();
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId(ID);
        routeDTO.setArrivalDate("2020-09-19 12:00");
        routeDTO.setStartDate("2020-09-18 12:00");

        BussDTO bussDTO = new BussDTO();
        bussDTO.setId(ID);
        bussDTO.setLastInspection("2020-08-08");
        bussDTO.setBussType(BussType.MINIVAN);
        routeDTO.setBuss(bussDTO);

        dto.setRoute(routeDTO);
        dtos.add(dto);

        List<TicketDTO> persistedTickets = service.addAllTickets(dtos);

        assertFalse(persistedTickets.isEmpty());
        assertEquals(dtos.size(), persistedTickets.size());
    }

    @Test
    void updateTicket() {
        when(ticketRepository.findById(ID)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        TicketDTO updated = service.updateTicket(dto, ID);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(dto.getPrice(), updated.getPrice());
    }

    @Test
    void shouldFindTicketById(){
        when(ticketRepository.findById(ID)).thenReturn(Optional.of(ticket));

        TicketDTO dto = service.findById(ID);

        assertNotNull(dto);
        assertEquals(ticket.getId(), dto.getId());
    }
}