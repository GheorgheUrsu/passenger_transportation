package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.RouteDTO;
import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.*;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.TicketType;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import com.passengertransportation.demo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final TicketRepository ticketRepository;


    @Override
    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                                        .map(route -> RouteMapper.INSTANCE.toDTO(route, new CycleAvoidingMappingContex()))
                                        .collect(Collectors.toList());
    }

    @Override
    public RouteDTO findByID(Long routeID) {
        Route route = routeRepository.findById(routeID)
                            .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        return RouteMapper.INSTANCE.toDTO(route, new CycleAvoidingMappingContex());
    }

    @Override
    public RouteDTO createRoute(RouteDTO routeDTO) {
        final Route route = RouteMapper.INSTANCE.fromDto(routeDTO, new CycleAvoidingMappingContex());
        final Route savedRoute  = routeRepository.save(route);
        return RouteMapper.INSTANCE.toDTO(savedRoute, new CycleAvoidingMappingContex());
    }

    @Override
    public RouteDTO updateRoute(RouteDTO routeDTO, Long routeID) {
        Route updatable = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));

        updatable.setArrivalDate(routeDTO.getArrivalDate());
        updatable.setStartDate(routeDTO.getStartDate());
        updatable.setStartLocation(routeDTO.getStartLocation());
        updatable.setArrivalDestination(routeDTO.getArrivalDestination());
        updatable.setBuss(BussMapper.INSTANCE.fromBussDto(routeDTO.getBuss(), new CycleAvoidingMappingContex()));
        routeRepository.save(updatable);

        return RouteMapper.INSTANCE.toDTO(updatable, new CycleAvoidingMappingContex());
    }

    @Override
    public RouteDTO deleteRouteByID(Long routeID) {
        Route deleted = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        routeRepository.deleteById(routeID);
        return RouteMapper.INSTANCE.toDTO(deleted, new CycleAvoidingMappingContex());
    }

    @Override
    public List<TicketDTO> findAllSoldTicket(Long routeId) {
       Route route =  routeRepository.findById(routeId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));

        return route.getTickets().stream()
                                 .filter(Objects::nonNull)
                                 .map(ticket -> TicketMapper.INSTANCE.toDTO(ticket, new CycleAvoidingMappingContex()))
                                 .collect(Collectors.toList());
    }

    @Override
    public TicketDTO updateTicketOnRoute(Long routeID, Long removed_ticketID, TicketDTO newTicketDto) {
        Route route = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));

        Optional<Ticket> deletable = route.getTickets().stream()
                                                       .filter(ticket -> ticket.getId().equals(removed_ticketID))
                                                       .findFirst();
        if(!deletable.isPresent()) {
            throw new ApplicationException(ExceptionType.TICKET_NOT_FOUND);
        }
            Ticket updatable = deletable.get();
            updatable.setPrice(newTicketDto.getPrice());
            updatable.setTicketType(newTicketDto.getTicketType());
            updatable.setPassenger(PassengerMapper.INSTANCE.fromDTO(newTicketDto.getPassenger(), new CycleAvoidingMappingContex()));
            updatable.setRoute(RouteMapper.INSTANCE.fromDto(newTicketDto.getRoute(), new CycleAvoidingMappingContex()));
            Ticket saved = ticketRepository.save(updatable);

        return TicketMapper.INSTANCE.toDTO(saved, new CycleAvoidingMappingContex());
    }

    @Override
    public List<TicketDTO> deleteAllTickets(Long routeID) {
        routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));

        Set<Ticket> tickets = ticketRepository.findAllByRouteId(routeID);
        tickets.forEach(ticketRepository::delete);

        return tickets.stream()
                      .map(ticket -> TicketMapper.INSTANCE.toDTO(ticket, new CycleAvoidingMappingContex()))
                      .collect(Collectors.toList());
    }

    @Override
    public TicketDTO deleteTicketFromRoute(Long routeID, Long ticketID) {
        final Route route = routeRepository.findById(routeID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
        final Ticket deletable = ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.TICKET_NOT_FOUND));

        ticketRepository.deleteById(ticketID);
        route.getTickets().remove(deletable);

        return TicketMapper.INSTANCE.toDTO(deletable, new CycleAvoidingMappingContex());
    }
}
