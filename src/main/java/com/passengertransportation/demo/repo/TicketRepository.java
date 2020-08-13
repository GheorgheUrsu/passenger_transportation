package com.passengertransportation.demo.repo;

import com.passengertransportation.demo.model.Ticket;
import com.passengertransportation.demo.model.enums.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByRouteId(Long routeId);
    List<Ticket> findAllByTicketType(TicketType ticketType);
}
