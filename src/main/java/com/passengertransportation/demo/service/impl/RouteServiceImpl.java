package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.BussMapper;
import com.passengertransportation.demo.mappers.RouteMapper;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                                        .map(RouteMapper.INSTANCE::toDTO)
                                        .collect(Collectors.toList());
    }

    @Override
    public RouteDTO findByID(Long routeID) {
        Route route = routeRepository.findById(routeID)
                            .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        return RouteMapper.INSTANCE.toDTO(route);
    }

    @Override
    public RouteDTO createRoute(RouteDTO routeDTO) {
        final Route route = RouteMapper.INSTANCE.fromDto(routeDTO);
        final Route savedRoute  = routeRepository.save(route);
        return RouteMapper.INSTANCE.toDTO(savedRoute);
    }

    @Override
    public RouteDTO updateRoute(RouteDTO routeDTO, Long routeID) {
        Route updatable = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));

        updatable.setArrivalDate(routeDTO.getArrivalDate());
        updatable.setStartDate(routeDTO.getStartDate());
        updatable.setStartLocation(routeDTO.getStartLocation());
        updatable.setArrivalDestination(routeDTO.getArrivalDestination());
        updatable.setBuss(BussMapper.INSTANCE.fromBussDto(routeDTO.getBuss()));
        routeRepository.save(updatable);

        return RouteMapper.INSTANCE.toDTO(updatable);
    }

    @Override
    public RouteDTO deleteRouteByID(Long routeID) {
        Route deleted = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        routeRepository.deleteById(routeID);
        return RouteMapper.INSTANCE.toDTO(deleted);
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
