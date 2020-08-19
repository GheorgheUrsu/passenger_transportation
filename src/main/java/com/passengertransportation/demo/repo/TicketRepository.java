package com.passengertransportation.demo.repo;

import com.passengertransportation.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Set<Ticket> findAllByRouteId(Long routeID);
}
