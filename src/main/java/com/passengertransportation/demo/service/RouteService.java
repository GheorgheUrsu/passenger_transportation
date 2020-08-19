package com.passengertransportation.demo.service;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;

import java.util.List;

public interface RouteService {

    List<RouteDTO> getAllRoutes();

    RouteDTO createRoute(RouteDTO routeDTO);

    RouteDTO findByID(Long routeID);

    RouteDTO updateRoute(RouteDTO routeDTO, Long routeID);

    RouteDTO deleteRouteByID(Long routeID);

    List<TicketDTO> findAllSoldTicket(Long routeId);

    TicketDTO updateTicketOnRoute(Long routeID, Long removed_ticketID, TicketDTO newTicketDto);

    List<TicketDTO> deleteAllTickets(Long routeID);

    TicketDTO deleteTicketFromRoute(Long routeID, Long ticketID);
}

