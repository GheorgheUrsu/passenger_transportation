package com.passengertransportation.demo.service;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.model.Ticket;

import java.util.List;

public interface RouteService {

    List<RouteDTO> getAllRoutes();

    RouteDTO createRoute(RouteDTO routeDTO);

    RouteDTO findByID(Long routeID);

    RouteDTO updateRoute(RouteDTO routeDTO, Long routeID);

    RouteDTO deleteRouteByID(Long routeID);

    List<Ticket> findAllSoldTicket(Long routeId);

    Ticket updateTicketOnRoute(Long routeID, Long removed_ticketID, Long new_ticketID);

    List<Ticket> deleteAllTickets(Long routeID);
}

