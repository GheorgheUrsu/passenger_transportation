package com.passengertransportation.demo.service;

import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoutes();

    Route createRoute(Route route);

    Route findByID(Long routeID);

    Route updateRoute(Route route, Long routeID);

    Route deleteRouteByID(Long routeID);

    List<Ticket> findAllSoldTicket(Long routeId);

    Ticket updateTicketOnRoute(Long routeID, Long removed_ticketID, Long new_ticketID);

    List<Ticket> deleteAllTickets(Long routeID);
}

