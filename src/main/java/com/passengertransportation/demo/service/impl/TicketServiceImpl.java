package com.passengertransportation.demo.service.impl;

import com.passengertransportation.demo.dto.TicketDTO;
import com.passengertransportation.demo.excepions.ApplicationException;
import com.passengertransportation.demo.excepions.ExceptionType;
import com.passengertransportation.demo.mappers.CycleAvoidingMappingContex;
import com.passengertransportation.demo.mappers.PassengerMapper;
import com.passengertransportation.demo.mappers.RouteMapper;
import com.passengertransportation.demo.mappers.TicketMapper;
import com.passengertransportation.demo.model.Buss;
import com.passengertransportation.demo.model.Route;
import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.TicketType;
import com.passengertransportation.demo.repo.BussRepository;
import com.passengertransportation.demo.repo.RouteRepository;
import com.passengertransportation.demo.repo.TicketRepository;
import com.passengertransportation.demo.service.TicketService;
import com.passengertransportation.demo.validators.CapacityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;
    private final BussRepository bussRepository;

    @Override
    public List<TicketDTO> getAllSoldTickets() {
        return ticketRepository.findAll().stream()
                .map(
                        ticket -> TicketMapper.INSTANCE.toDTO(ticket, new CycleAvoidingMappingContex()))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO deleteTicketByID(Long ticketID) {
        final Ticket deleted = ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.TICKET_NOT_FOUND));
        ticketRepository.deleteById(ticketID);
        return TicketMapper.INSTANCE.toDTO(deleted, new CycleAvoidingMappingContex());
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO, Long ticketID) {
        final Ticket updatable = ticketRepository.findById(ticketID)
                .orElseThrow(() -> new ApplicationException(ExceptionType.TICKET_NOT_FOUND));

        updatable.setPassenger(PassengerMapper.INSTANCE.fromDTO(ticketDTO.getPassenger(), new CycleAvoidingMappingContex()));
        updatable.setRoute(RouteMapper.INSTANCE.fromDto(ticketDTO.getRoute(), new CycleAvoidingMappingContex()));
        updatable.setPrice(ticketDTO.getPrice());
        //TODO from string to enum find a better solution
        updatable.setTicketType((ticketDTO.getTicketType().equals(TicketType.BUSINESS.toString())) ? TicketType.BUSINESS : TicketType.ECONOM);
        ticketRepository.save(updatable);

        return TicketMapper.INSTANCE.toDTO(updatable, new CycleAvoidingMappingContex());
    }

    @Override
    public List<TicketDTO> addAllTickets(List<TicketDTO> ticketDTOS) {
        List<TicketDTO> acceptedTickets = new ArrayList<>();
        for (TicketDTO ticketDTO : ticketDTOS) {
            Long routeID = ticketDTO.getRoute().getId();
            Long bussID = ticketDTO.getRoute().getBuss().getId();
            Route route = routeRepository.findById(routeID)
                    .orElseThrow(() -> new ApplicationException(ExceptionType.ROUTE_NOT_FOUND));
            Set<Ticket> tickets = ticketRepository.findAllByRouteId(routeID);

            final Buss buss = bussRepository.findById(bussID)
                    .orElseThrow(() -> new ApplicationException(ExceptionType.BUSS_NOT_FOUND));

            if (CapacityValidator.hasSeats(tickets, buss.getBussType())) {
                Ticket ticket = TicketMapper.INSTANCE.fromDTO(ticketDTO, new CycleAvoidingMappingContex());
                route.addTicket(ticket);
                routeRepository.save(route);
                ticket.setRoute(route);
                Ticket createdTicket = ticketRepository.save(ticket);
                acceptedTickets.add(TicketMapper.INSTANCE.toDTO(createdTicket, new CycleAvoidingMappingContex()));
            } else {
                throw new ApplicationException(ExceptionType.NO_MORE_AVAILABLE_TICKETS);
            }
        }
        return acceptedTickets;
    }
}
