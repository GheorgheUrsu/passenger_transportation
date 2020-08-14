package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route findByID(Long routeID) {
        return routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route, Long routeID) {
        Route updatableRoute = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        updatableRoute.setBuss(route.getBuss());
        updatableRoute.setArrivalDestination(route.getArrivalDestination());
        updatableRoute.setStartLocation(route.getStartLocation());
        updatableRoute.setStartDate(route.getStartDate());
        updatableRoute.setArrivalDate(route.getArrivalDate());
        return routeRepository.save(route);
    }

    @Override
    public Route deleteRouteByID(Long routeID) {
        Route deleted = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        routeRepository.deleteById(routeID);
        return deleted;
    }

    @Override
    public List<Ticket> findAllSoldTicket(Long routeId) {
        /*routeRepository.findById(routeId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        return ticketRepository.findAllByRouteId(routeId)
                               .stream()
                               .filter(Objects::nonNull)
                               .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public Ticket updateTicketOnRoute(Long routeID, Long removed_ticketID, Long new_ticketID) {
        //TODO
        return null;
    }

    @Override
    public List<Ticket> deleteAllTickets(Long routeID) {
       /* routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        List<Ticket> tickets = ticketRepository.findAllByRouteId(routeID);
        tickets.forEach(ticket -> ticket.setRoute(null));
        tickets.forEach(ticketRepository::save);
        return ticketRepository.findAllByRouteId(routeID);*/
        return null;
    }
}
