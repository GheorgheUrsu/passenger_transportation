package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import com.passengertransportation.demo.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    private final static Long ID = 1L;

    private RouteService service;
    private Route route;
    private RouteDTO dto;

    @Mock
    private RouteRepository routeRepository;
    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new RouteServiceImpl(routeRepository, ticketRepository);

        route = new Route();
        route.setId(ID);
        route.setStartDate("2020-09-09 10:00");
        route.setArrivalDate("2020-09-10 10:00");

        dto = new RouteDTO();
        dto.setId(ID);
        dto.setStartDate("2020-09-09 10:00");
        dto.setArrivalDate("2020-09-10 10:00");
    }

    @Test
    void getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(route);

        when(routeRepository.findAll()).thenReturn(routes);

        List<RouteDTO> dtos = service.getAllRoutes();

        assertFalse(dtos.isEmpty());
        assertEquals(routes.size(), dtos.size());
        verify(routeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByID() {
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        RouteDTO fromDB = service.findByID(ID);

        assertNotNull(fromDB);
        assertEquals(route.getId(), fromDB.getId());
        verify(routeRepository, Mockito.times(1)).findById(ID);
    }

    @Test
    void createRoute() {
        when(routeRepository.save(route)).thenReturn(route);
        RouteDTO persistedRoute = service.createRoute(dto);

        assertNotNull(persistedRoute);
        assertEquals(route.getId(), persistedRoute.getId());
    }

    @Test
    void updateRoute() {
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        when(routeRepository.save(route)).thenReturn(route);

        dto.setStartLocation("Nevada");
        RouteDTO updated = service.updateRoute(dto, ID);

        assertNotNull(updated);
        assertEquals(route.getId(), updated.getId());
        assertEquals(route.getStartLocation(), updated.getStartLocation());
    }

    @Test
    void deleteRouteByID() {
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        routeRepository.deleteById(ID);
        verify(routeRepository, times(1)).deleteById(ID);

        RouteDTO deleted = service.deleteRouteByID(ID);

        assertEquals(ID, deleted.getId());
    }

    @Test
    void findAllSoldTicketForSpecificRoute() {
        Ticket ticket = new Ticket();
        ticket.setId(ID);
        ticket.setPrice(100);

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket);

        route.setTickets(tickets);
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));

        Set<TicketDTO> dtos = service.findAllSoldTicketByBuss(ID);

        assertFalse(dtos.isEmpty());
        assertEquals(dtos.size(), tickets.size());
    }

    @Test
    void updateTicketOnRoute() {
        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket = new Ticket();
        ticket.setId(ID);
        ticket.setPrice(100);

        tickets.add(ticket);

        route.setTickets(tickets);

        TicketDTO updatable = new TicketDTO();
        updatable.setPrice(150);
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        TicketDTO updated = service.updateTicketOnRoute(ID, ID, updatable);

        assertNotNull(updated);
        assertEquals(150, updated.getPrice());
    }

    @Test
    void deleteAllTickets() {
        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket = new Ticket();
        ticket.setId(ID);
        ticket.setPrice(100);

        tickets.add(ticket);

        route.setTickets(tickets);

        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        when(ticketRepository.findAllByRouteId(ID)).thenReturn(tickets);

        List<TicketDTO> deleted = service.deleteAllTicketsByRouteId(ID);

        assertEquals(1, deleted.size());

    }

    @Test
    void deleteTicketFromRoute() {
        when(routeRepository.findById(ID)).thenReturn(Optional.of(route));
        Ticket ticket = new Ticket();
        ticket.setId(ID);
        ticket.setPrice(100);
        when(ticketRepository.findById(ID)).thenReturn(Optional.of(ticket));

        TicketDTO deleted = service.deleteTicketFromRoute(ID, ID);

        assertNotNull(deleted);
        assertEquals(ID, deleted.getId());
    }
}