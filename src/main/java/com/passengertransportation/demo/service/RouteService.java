package com.passengertransportation.demo.service;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;

import java.util.List;
import java.util.Set;

public interface RouteService {

    List<RouteDTO> getAllRoutes();

    RouteDTO createRoute(RouteDTO routeDTO);

    RouteDTO findByID(Long routeID);

    RouteDTO updateRoute(RouteDTO routeDTO, Long routeID);

    RouteDTO deleteRouteByID(Long routeID);

    Set<TicketDTO> findAllSoldTicketByBuss(Long routeId);

    TicketDTO updateTicketOnRoute(Long routeID, Long removed_ticketID, TicketDTO newTicketDto);

    List<TicketDTO> deleteAllTicketsByRouteId(Long routeID);

    TicketDTO deleteTicketFromRoute(Long routeID, Long ticketID);
}

